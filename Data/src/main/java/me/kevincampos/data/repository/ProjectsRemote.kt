package me.kevincampos.data.repository

import io.reactivex.Observable
import me.kevincampos.data.model.ProjectEntity

interface ProjectsRemote {

    fun getProjects() : Observable<List<ProjectEntity>>

}