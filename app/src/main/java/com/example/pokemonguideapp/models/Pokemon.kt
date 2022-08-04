package com.example.pokemonguideapp.models

import com.google.gson.annotations.SerializedName

data class Pokemon (
    val id: Long,
    val name: String,
    @SerializedName("base_experience")
    val baseExperience: Long,
    val height: Long,
    @SerializedName("is_default")
    val isDefault: Boolean,
    val order: Long,
    val weight: Long,
    val abilities: List<Ability>,
    val forms: List<Species>,
    @SerializedName("game_indices")
    val gameIndices: List<GameIndex>,
    @SerializedName("held_items")
    val heldItems: List<HeldItem>,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,
    val moves: List<Move>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    @SerializedName("past_types")
    val pastTypes: List<PastType>
)

data class Ability (
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val slot: Long,
    val ability: Species
)

data class Species (
    val name: String,
    val url: String
)

data class GameIndex (
    @SerializedName("game_index")
    val gameIndex: Long,
    val version: Species
)

data class HeldItem (
    val item: Species,
    @SerializedName("version_details")
    val versionDetails: List<VersionDetail>
)

data class VersionDetail (
    val rarity: Long,
    val version: Species
)

data class Move (
    val move: Species,
    @SerializedName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>
)

data class VersionGroupDetail (
    @SerializedName("level_learned_at")
    val levelLearnedAt: Long,
    @SerializedName("version_group")
    val versionGroup: Species,
    @SerializedName("move_learn_method")
    val moveLearnMethod: Species
)

data class PastType (
    val generation: Species,
    val types: List<Type>
)

data class Type (
    val slot: Long,
    val type: Species
)

data class GenerationV (
    @SerializedName("black-white")
    val blackWhite: Sprites
)

data class GenerationIv (
    @SerializedName("diamond-pearl")
    val diamondPearl: Sprites,
    @SerializedName("heartgold-soulsilver")
    val heartgoldSoulsilver: Sprites,
    val platinum: Sprites
)

data class Versions (
    @SerializedName("generation-i")
    val generationI: GenerationI,
    @SerializedName("generation-ii")
    val generationIi: GenerationIi,
    @SerializedName("generation-iii")
    val generationIii: GenerationIii,
    @SerializedName("generation-iv")
    val generationIv: GenerationIv,
    @SerializedName("generation-v")
    val generationV: GenerationV,
    @SerializedName("generation-vi")
    val generationVi: Map<String, Home>,
    @SerializedName("generation-vii")
    val generationVii: GenerationVii,
    @SerializedName("generation-viii")
    val generationViii: GenerationViii
)

data class Sprites (
    @SerializedName("back_default")
    val backDefault: String? = null,
    @SerializedName("back_female")
    val backFemale: String? = null,
    @SerializedName("back_shiny")
    val backShiny: String? = null,
    @SerializedName("back_shiny_female")
    val backShinyFemale: String? = null,
    @SerializedName("front_default")
    val frontDefault: String? = null,
    @SerializedName("front_female")
    val frontFemale: String? = null,
    @SerializedName("front_shiny")
    val frontShiny: String? = null,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: String? = null,
    val other: Other? = null,
    val versions: Versions? = null,
    val animated: Sprites? = null
)

data class GenerationI (
    @SerializedName("red-blue")
    val redBlue: RedBlue,
    val yellow: RedBlue
)

data class RedBlue (
    @SerializedName("back_default")
    val backDefault: String? = null,
    @SerializedName("back_gray")
    val backGray: String? = null,
    @SerializedName("front_default")
    val frontDefault: String? = null,
    @SerializedName("front_gray")
    val frontGray: String? = null
)

data class GenerationIi (
    val crystal: Crystal,
    val gold: Crystal,
    val silver: Crystal
)

data class Crystal (
    @SerializedName("back_default")
    val backDefault: String? = null,
    @SerializedName("back_shiny")
    val backShiny: String? = null,
    @SerializedName("front_default")
    val frontDefault: String? = null,
    @SerializedName("front_shiny")
    val frontShiny: String? = null
)

data class GenerationIii (
    val emerald: Emerald,
    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: Crystal,
    @SerializedName("ruby-sapphire")
    val rubySapphire: Crystal
)

data class Emerald (
    @SerializedName("front_default")
    val frontDefault: String? = null,
    @SerializedName("front_shiny")
    val frontShiny: String? = null
)

data class Home (
    @SerializedName("front_default")
    val frontDefault: String? = null,
    @SerializedName("front_female")
    val frontFemale: String? = null,
    @SerializedName("front_shiny")
    val frontShiny: String? = null,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: String? = null
)

data class GenerationVii (
    val icons: DreamWorld,
    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: Home
)

data class DreamWorld (
    @SerializedName("front_default")
    val frontDefault: String? = null,
    @SerializedName("front_female")
    val frontFemale: String? = null
)

data class GenerationViii (
    val icons: DreamWorld
)

data class Other (
    @SerializedName("dream_world")
    val dreamWorld: DreamWorld,
    val home: Home,
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork
)

data class OfficialArtwork (
    @SerializedName("front_default")
    val frontDefault: String
)

data class Stat (
    @SerializedName("base_stat")
    val baseStat: Long,
    val effort: Long,
    val stat: Species
)