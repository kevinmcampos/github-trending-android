package me.kevincampos.data

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import me.kevincampos.data.mapper.ProjectMapper
import me.kevincampos.data.repository.ProjectsCache
import me.kevincampos.data.store.ProjectsDataStoreFactory
import me.kevincampos.domain.model.Project
import me.kevincampos.domain.repository.ProjectsRepository
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(
        private val projectMapper: ProjectMapper,
        private val factory: ProjectsDataStoreFactory,
        private val cache: ProjectsCache)
    : ProjectsRepository {

    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(cache.areProjectsCached().toObservable(),
                cache.isProjectsCachedExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    factory.getDataStore(it.first, it.second).getProjects()
                }
                .flatMap { projects ->
                    factory.getCacheDataStore()
                            .saveProjects(projects)
                            .andThen(Observable.just(projects))
                }
                .map { projects ->
                    projects.map { projectEntity ->
                        projectMapper.mapFromEntity(projectEntity)
                    }
                }
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        return factory.getCacheDataStore().getBookmarkedProjects().map { projects ->
            projects.map { projectEntity ->
                projectMapper.mapFromEntity(projectEntity)
            }
        }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsNotBookmarked(projectId)
    }

}