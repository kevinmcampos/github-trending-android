package me.kevincampos.data.store

import me.kevincampos.data.repository.ProjectsDataSource
import javax.inject.Inject

open class ProjectsDataStoreFactory @Inject constructor(
        private val projectsCacheDataStore: ProjectsCacheDataStore,
        private val projectsRemoteDataStore: ProjectsRemoteDataStore
) {

    open fun getDataStore(projectsCached: Boolean, cacheExpired: Boolean): ProjectsDataSource {
        return if (projectsCached && !cacheExpired) {
            projectsCacheDataStore
        } else {
            projectsRemoteDataStore
        }
    }

    open fun getCacheDataStore() : ProjectsDataSource {
        return projectsCacheDataStore
    }

}