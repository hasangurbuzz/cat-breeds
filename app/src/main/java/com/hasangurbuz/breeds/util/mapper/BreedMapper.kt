package com.hasangurbuz.breeds.util.mapper

import com.hasangurbuz.breeds.domain.model.Breed
import com.hasangurbuz.breeds.domain.model.FavBreed

class BreedMapper : Mapper<Breed, FavBreed> {
    override fun fromEntity(entity: FavBreed): Breed {
        return Breed(
            id = entity.id,
            wikipediaUrl = entity.wikipediaUrl,
            favorite = entity.favorite,
            adaptability = entity.adaptability,
            origin = entity.origin,
            lifeSpan = entity.lifeSpan,
            image = entity.image,
            dogFriendly = entity.dogFriendly,
            description = entity.description,
            name = entity.name

        )

    }

    override fun toEntity(domain: Breed): FavBreed {
        return FavBreed(
            id = domain.id,
            wikipediaUrl = domain.wikipediaUrl,
            favorite = domain.favorite,
            adaptability = domain.adaptability,
            origin = domain.origin,
            lifeSpan = domain.lifeSpan,
            image = domain.image,
            dogFriendly = domain.dogFriendly,
            description = domain.description,
            name = domain.name
        )
    }
}