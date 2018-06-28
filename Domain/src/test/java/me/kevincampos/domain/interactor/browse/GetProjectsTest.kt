package me.kevincampos.domain.interactor.browse

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

class GetProjectsTest {

    lateinit var getProjects: GetProjects
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getProjects = GetProjects(projectsRepository, postExecutionThread)
    }

    @Test
    fun getProjectsCompletes() {
        val projects = Observable.just(ProjectDataFactory.makeListOfProjects(2))
        stubGetProjects(projects)

        val test = getProjects.buildUseCaseObservable().test()
        test.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val projects = ProjectDataFactory.makeListOfProjects(2)
        stubGetProjects(Observable.just(projects))

        val test = getProjects.buildUseCaseObservable().test()
        test.assertValue(projects)
    }

    private fun stubGetProjects(observable: Observable<List<Project>>) {
        whenever(projectsRepository.getProjects())
                .thenReturn(observable)
    }

}