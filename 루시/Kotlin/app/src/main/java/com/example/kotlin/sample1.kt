package com.example.kotlin


fun main(){
    // string template
    // ${변수명}
    // $(달러)를 사용해야  할 땐 \$
    val name = "kihyun"
    println("my name is ${name}")
}

// 함수 사용
// fun [함수이름](){}
// Unit = void , Unit 생략 가능

fun helloworld() : Unit {
    println("Hello World!")
}

// 자료형 첫글자 대문자
// : 뒤에 자료형
// 반환될 값이 있을땐 자료형 명시
fun add(a : Int , b : Int) : Int{
    return a + b
}


// val , var
// val = value ( 상수 )
// var = variable ( 변수 )

fun h1(){
    val a : Int
    var b : Int = 9

    // 에러발생
    //a = 100

    b = 100

    // 초기화를 바로 할 떈 자료형이 생략 가능 ( 자동추론 )
    // 초기화를 하지 않으면 자료형 명시 필수

    var c =9
    var name = "kihyun"
}










