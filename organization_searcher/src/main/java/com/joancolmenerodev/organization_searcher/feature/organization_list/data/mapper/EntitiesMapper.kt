package com.joancolmenerodev.organization_searcher.feature.organization_list.data.mapper

import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model.RepositoriesByOrganization
import com.joancolmenerodev.persistence.entities.Repository

fun Repository.map(): RepositoriesByOrganization =
    RepositoriesByOrganization(
        this.id,
        this.name,
        this.description,
        this.url,
        this.forked,
        this.ownerName,
        this.ownerAvatar,
        this.ownerURL
    )
