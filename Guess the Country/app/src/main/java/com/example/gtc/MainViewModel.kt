package com.example.gtc

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import com.example.gtc.model.maxWords
import com.example.gtc.model.scoreIncrease
import com.example.gtc.model.wordList
import com.example.gtc.model.GameUiState

class MainViewModel : ViewModel() {

    private var _gameUiState= mutableStateOf(GameUiState())
    val uiState: State<GameUiState> = _gameUiState

    var usedWords=mutableListOf<String>()

    var message=""
    var endMessage=""

    init{
        resetGame()
    }

    fun pickWord() {
        var currentWord: String
        var currentHint:String
        do {
            val currentEntry = wordList.random()
            currentWord=currentEntry.word
            currentHint=currentEntry.hint
        } while (usedWords.contains(currentWord))

        usedWords.add(currentWord)
        _gameUiState.value = _gameUiState.value.copy(originalWord = currentWord,
            originalHint = currentHint,
            wordCount = _gameUiState.value.wordCount+1)
    }

    fun shuffleWord() {
        val scrambledArr = _gameUiState.value.originalWord.toCharArray().toList()
        val shuffled = scrambledArr.shuffled()

        if (scrambledArr == shuffled) {
            shuffleWord()
        } else {
            _gameUiState.value = _gameUiState.value.copy(scrambledWord = (shuffled.joinToString("")).lowercase())
        }
    }

    fun skipWord() {
        if (_gameUiState.value.wordCount == maxWords) {
            _gameUiState.value = _gameUiState.value.copy(isGameOver = true)
            message=""

            endMessage = if(_gameUiState.value.score>=80) {
                "Congratulations,You Won!"
            } else{
                "You Lost,Try Again"
            }
        } else {
            message="Onto the Next One"
            pickWord()
            shuffleWord()

        }
    }

    fun checkAnswer(guess:String){
        if(guess.lowercase()==(_gameUiState.value.originalWord).lowercase()){
            _gameUiState.value = _gameUiState.value.copy(score = _gameUiState.value.score+ scoreIncrease)
            message="Correct! Onto the next one"
            pickWord()
            shuffleWord()
            if (_gameUiState.value.wordCount == maxWords) {
                _gameUiState.value = _gameUiState.value.copy(isGameOver = true)

                endMessage = if(_gameUiState.value.score>=80) {
                    "Congratulations,You Won!"
                } else{
                    "You Lost,Try Again"
                }
            }
        }
        else{
            message="Oh No! Try Again or Skip"
        }
    }

    fun resetGame(){
        usedWords.clear()
        _gameUiState.value = _gameUiState.value.copy(isGameOver = false,
            score=0,
            wordCount = 0)
        pickWord()
        shuffleWord()
    }


}