package com.example.gtc.model


data class DataModel(
    val word: String,
    val hint: String = ""
)

data class GameUiState(
    val originalWord: String = "",
    val originalHint: String="",
    val scrambledWord: String = "",
    val score: Int = 0,
    val wordCount: Int = 0,
    val isGameOver: Boolean = false
)

const val maxWords=10
const val scoreIncrease=20

val wordList = listOf(
    DataModel("Argentina", "Home of Tango"),
    DataModel("Australia", "Famous for Kangaroos"),
    DataModel("Austria", "Birthplace of Mozart"),
    DataModel("Bangladesh", "Land of Rivers"),
    DataModel("Belgium", "Known for Waffles and Chocolates"),
    DataModel("Brazil", "Famous for Carnival"),
    DataModel("Canada", "Maple Leaf Symbol"),
    DataModel("Chile", "World's Longest Country"),
    DataModel("China", "Land of the Great Wall"),
    DataModel("Colombia", "Coffee Powerhouse"),
    DataModel("Denmark", "Origin of LEGO"),
    DataModel("Egypt", "Land of Pyramids"),
    DataModel("Finland", "Land of a Thousand Lakes"),
    DataModel("France", "Home to the Eiffel Tower"),
    DataModel("Germany", "Famous for Autobahns"),
    DataModel("Greece", "Cradle of Democracy"),
    DataModel("Hungary", "Danube River Runs Through"),
    DataModel("India", "Birthplace of Yoga"),
    DataModel("Indonesia", "World's Largest Island Country"),
    DataModel("Ireland", "Emerald Isle"),
    DataModel("Israel", "Home to Jerusalem"),
    DataModel("Italy", "Boot-shaped Country"),
    DataModel("Japan", "Land of the Rising Sun"),
    DataModel("Kenya", "Known for Safaris"),
    DataModel("Malaysia", "Petronas Towers Country"),
    DataModel("Mexico", "Land of Tacos"),
    DataModel("Morocco", "Known for Deserts and Bazaars"),
    DataModel("Nepal", "Home to Mount Everest"),
    DataModel("Netherlands", "Land of Windmills"),
    DataModel("Nigeria", "Most Populous in Africa"),
    DataModel("Norway", "Land of Fjords"),
    DataModel("Pakistan", "Home of the Karakoram Highway"),
    DataModel("Peru", "Home to Machu Picchu"),
    DataModel("Poland", "Capital is Warsaw"),
    DataModel("Portugal", "Famous for Pastel de Nata"),
    DataModel("Russia", "Largest Country by Area"),
    DataModel("Singapore", "Lion City"),
    DataModel("Spain", "Land of Flamenco"),
    DataModel("Sweden", "Famous for IKEA"),
    DataModel("Switzerland", "Known for Neutrality"),
    DataModel("Thailand", "Land of Smiles"),
    DataModel("Turkey", "Where Europe Meets Asia"),
    DataModel("Ukraine", "Breadbasket of Europe"),
    DataModel("Uruguay", "First World Cup Host"),
    DataModel("Vietnam", "Famous for Pho")
)






