package me.kevincampos.domain.interactor.bookmark

import io.reactivex.Completable
import me.kevincampos.domain.executor.PostExecutionThread
import me.kevincampos.domain.interactor.CompletableUseCase
import me.kevincampos.domain.repository.ProjectsRepository
import javax.inject.Inject

class BookmarkProject @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread
) : CompletableUseCase<BookmarkProject.Params>(postExecutionThread) {

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null")
        return projectsRepository.bookmarkProject(params.projectId)
    }

    data class Params constructor(val projectId: String) {
        companion object {
            fun forProject(projectId: String): Params {
                return Params(projectId)
            }
        }
    }
}