package me.kevincampos.domain.interactor.bookmark

import io.reactivex.Observable
import me.kevincampos.domain.executor.PostExecutionThread
import me.kevincampos.domain.interactor.ObservableUseCase
import me.kevincampos.domain.model.Project
import me.kevincampos.domain.repository.ProjectsRepository
import javax.inject.Inject

class GetBookmarkedProjects @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Project>, Nothing>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getBookmarkedProjects()
    }
}