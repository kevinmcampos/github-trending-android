package me.kevincampos.domain.interactor.bookmark

import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import me.kevincampos.domain.executor.PostExecutionThread
import me.kevincampos.domain.model.Project
import me.kevincampos.domain.repository.ProjectsRepository
import me.kevincampos.domain.test.ProjectDataFactory
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBookmarkedProjectsTest {

    private lateinit var getBookmarkedProjects: GetBookmarkedProjects

    @Mock
    lateinit var projectsRepository: ProjectsRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBookmarkedProjects = GetBookmarkedProjects(projectsRepository, postExecutionThread)
    }

    @Test
    fun getBookmarkedCompletes() {
        val projects = ProjectDataFactory.makeListOfProjects(2)
        stubProjectsRepository(Observable.just(projects))

        val observableTest = getBookmarkedProjects.buildUseCaseObservable().test()
        observableTest.assertComplete()
    }

    @Test
    fun getBookmarkedReturns() {
        val projects = ProjectDataFactory.makeListOfProjects(2)
        stubProjectsRepository(Observable.just(projects))

        val observableTest = getBookmarkedProjects.buildUseCaseObservable().test()
        observableTest.assertValue(projects)
    }

    private fun stubProjectsRepository(observable: Observable<List<Project>>) {
        whenever(projectsRepository.getBookmarkedProjects())
                .thenReturn(observable)
    }
}