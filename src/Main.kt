import kotlinx.coroutines.delay

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

suspend fun main()
{
    println("Программа «Кофе – машина».")
    SelectedMenu()
}

suspend fun SelectedMenu() {
    val listCoofees = listOf<String>("Americano", "Cappuccino" , "Latte")

    var coffeeName = "";
    while (true) {
        println("Выберите кофе:")
        coffeeName = readln().toString()
        if (listCoofees.contains(coffeeName)) break;
        else println("Выбранного кофе нет в асортименте.")
    }

    println(coffeeName)
    val coffeeNameObject = Coffee.Name(coffeeName);
    coffeeNameObject.outputCoffeeName()

    println("Количество сахара:")
    val quantShugar = readln().toInt()
    val quantShugarObject = Coffee.QuantShugar(quantShugar);
    quantShugarObject.outputQuantShugar()

    var volumeMilkObject = Coffee.VolumeMilk(0.0)
    if (coffeeName == "Cappuccino") {
        println("ОбЪем молока литров:")
        val volumeMilk = readln().toDouble()

        volumeMilkObject = Coffee.VolumeMilk(volumeMilk);
        volumeMilkObject.outputVolumeMilk()
    }

    println("ОбЪем кофе литров:")
    val volumeCoffee = readln().toDouble()
    val volumeCoffeeObject = Coffee.VolumeCoffee(volumeCoffee);
    volumeCoffeeObject.outputVolumeCoffee()

    var prepareString = "";
    prepareString = SelectMenu(coffeeNameObject, prepareString)
    prepareString = SelectMenu(quantShugarObject, prepareString)
    prepareString = SelectMenu(volumeMilkObject, prepareString)
    prepareString = SelectMenu(volumeCoffeeObject, prepareString)
    print("90%100%\n")
    delay(100L)
    prepareString = "Ваш кофе готов: " + prepareString;
    println(prepareString.toString())
}

suspend fun SelectMenu(menu: Coffee, prepareString: String):String
{
    when (menu) {
        is Coffee.Name -> {
            print("10%20%")
            delay(100L)
            return prepareString + menu.coffeeName+", "
        }
        is Coffee.QuantShugar -> {
            print("30%40%")
            delay(100L)
            return prepareString + "сахар:" + menu.quantShugar.toString()+", "
        }
        is Coffee.VolumeMilk -> {
            print("50%60%")
            delay(100L)
            if (menu.volumeMilk > 0.0) return prepareString + "обЪем молока:" + menu.volumeMilk + ", "
            else return prepareString
        }
        is Coffee.VolumeCoffee -> {
            print("70%80%")
            delay(100L)
            return prepareString + "обЪем воды:" + menu.volumeCoffee+"."
        }
    }
}

val Coffee.Name.coffeeName
    get() = ""

val Coffee.QuantShugar.quantShugar
    get() = 0

val Coffee.VolumeMilk.volumeMilk
    get() = 0.0

val Coffee.VolumeCoffee.volumeCoffee
    get() = 0.0

sealed class Coffee() {
    class Name(val coffeeName: String) : Coffee(){
        fun  outputCoffeeName() {}
    }
    class QuantShugar(val quantShugar: Int) : Coffee() {
        fun outputQuantShugar() {}
    }
    class VolumeMilk(val volumeMilk: Double) : Coffee() {
        fun outputVolumeMilk() {}
    }
    class VolumeCoffee(val volumeCoffee: Double): Coffee() {
        fun outputVolumeCoffee() {}
    }
}