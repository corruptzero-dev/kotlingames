import kotlin.random.Random

class Games {
    private var currentgame: (() -> Unit)? = null
    private var random: Int = 0
    fun play(){
        print("В какую игру хотите поиграть?\n1. Угадай число\n2. Бомбы\n3. Монетка\nВведите цифру: ")
        when(readLine()!!.toInt()){
            1 -> currentgame = { guess() }
            2 -> currentgame = { bombs() }
            3 -> currentgame = { coin() }
        }
        currentgame!!.invoke()
    }

    private fun coin(){
        print("Орел или решка? (0/1): ")
        when(Random.nextBoolean()) {
            readLine()!!.toBoolean() -> println("Вы выиграли!")
            else -> println("Вы проиграли.")
        }
        askUser()
    }

    private fun guess(){
        random = Random.nextInt(0,100)
        while (true){
            print("Угадайте число в диапазоне 0-100: ")
            val userInput: Int = readLine()!!.toInt()
            when {
                userInput!=random -> if (userInput<random)
                    println("Загаданное число больше.")
                else println("Загаданное число меньше.")
                else -> break
            }
        }
        println("Вы выиграли!")
        askUser()
    }
    private fun bombs(){
        for (i in 6 downTo 1 step 2){
            random = Random.nextInt(0,i)
            print("В одной из $i ячеек лежит бомба.\nУгадайте в какой её нет: ")
            when (random) {
                readLine()!!.toInt() -> {
                    println("Вы проиграли!")
                    askUser()
                }
                else -> {
                    println("Верно. Идем дальше.")
                }
            }
        }
        println("Вы выиграли!")
        askUser()
    }
    private fun askUser(){
        println("Что дальше?")
        print("1. Играть заново\n2. Вернуться к списку игр\nВведите цифру: ")
        when(readLine()!!.toInt()){
            1 -> currentgame!!.invoke()
            2 -> play()
        }
    }
}
