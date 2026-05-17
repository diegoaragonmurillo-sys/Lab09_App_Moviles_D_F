package com.example.lab09

data class CharacterResponse(
    val results: List<CharacterModel>
)

data class CharacterModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val origin: Origin
)

data class Origin(
    val name: String
)