package me.kevincampos.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import me.kevincampos.data.model.ProjectEntity
import me.kevincampos.data.repository.ProjectsDataStore
import me.kevincampos.data.repository.ProjectsRemote
import javax.inject.Inject

open class ProjectsRemoteDataStore @Inject constructor(
        private val projectsRemote: ProjectsRemote
): ProjectsDataStore {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsRemote.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("Saving projects isn't supported here...")
    }

    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("Cleaning projects isn't supported here...")
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("Getting bookmarked projects isn't supported here...")
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Setting bookmarks isn't supported here...")
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Setting bookmarks isn't supported here...")
    }

}