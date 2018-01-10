package com.example.hp.knowlgdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.hp.knowlgdemo.utils.LogUtils
import kotlinx.android.synthetic.main.activity_koilition.*
import java.lang.reflect.Array

class KoilitionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_koilition)
        aaa.setText("sss")
        kbC(1, 2, 3, 4)
        var map= mapOf<String,String>()
        val s = map["ss"]
        val hashMap = HashMap<String, String>()()
        val toMutableMap = map.toMutableMap()
        toMutableMap["ss"]=""
        val hashMapOf = hashMapOf<String, String>()
        val mutableMapOf = mutableMapOf<String, String>()
        mutableMapOf[""]=""
        val listOf = listOf<String>()
        val arrayListOf = arrayListOf<String>()
        arrayListOf.add("")

        var hashMapOf1 = hashMapOf<String,String>()
      //  声明一个Map:
        var mapTem = mapOf<String, Int>();
        (mapTem as HashMap).put("value", 1);
     //   声明一个可变的Map：
        var hashMaps = hashMapOf<String, Boolean>();
        hashMaps.put("value", false);

        val hashSetOf = hashSetOf(String())
        hashSetOf.add("")

        var sss=hashMapOf<String,String>()

        val hashMapOf2 = kotlin.collections.hashMapOf<String, String>()
        hashMapOf2.put("","")
        (listOf as ArrayList).add("")
        val toMutableList = listOf.toMutableList()
        toMutableList[1]="ss"
        val arrayOf = arrayOf(1, 2, 3)
        for (a in arrayOf.iterator()){

        }
    }

    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun add_(a: Int, b: Int) = a + b  //私有方法可以不写返回类型

    public fun add_2(a: Int, b: Int): Int = a + b  //public必须明确写出返回类型

    //void方法

    fun prient(): Unit {
        LogUtils.e("=====", "")
    }

    fun prient_() {
        LogUtils.e("====", "")
    }

    public fun prient_2() {
        LogUtils.e("====", "")
    }

    //可变参数
    fun kbC(vararg c: Int) {
        for (d in c) {
            LogUtils.e("======", "" + d)
//            sumLambda(1,2)
            val i = e + 1
            f += 1
        }
    }

    //lambda表达式  val 类似final  var可变
    val sumLambda: (Int, Int) -> Int = { a, b -> a + b }


    //常量

    val a = 1
    val b: Int = 0

    var c = 1
    var e: Int = 0

    var f = "$a"

    var i: String? = ""
    var h = i!!.toInt()
    var h1 = i?.toInt()
    var h2 = i?.toInt() ?: -1

    fun prient_4() {
        for (i in 1..4) LogUtils.e("=========", "ss" + i)  //输出1234
        for (i in 4..1) LogUtils.e("============", "ss" + i)  //什么都不输出
        for (i in 1..4 step 2) LogUtils.e("========", "ss" + i)  //输出13
        for (i in 4 downTo 1 step 2) LogUtils.e("=======", "ss" + i)  //输出42
        for (i in 1 until 10) LogUtils.e("====", "ss" + i) //输出1-9

    }

    fun prient_5() {
        for (i in 1..4 step 2) LogUtils.e("========", "ss" + i)  //输出13

        val a = arrayOf(1, 2, 3)
        val b = Array(3, { i -> i * 2 })
        val c: (String, String) -> String = { s, s1 -> s + s1 }

        var d = Array(4, { i -> i * 2 })
        var m: (Int, Int) -> Int = { c, b -> c + b }
        var z: IntArray = intArrayOf(1, 2, 3)
        var cc = IntArray(3, { i -> i * 2 })
        var ccc = """cc""".trim()
        var aa = if (5 > 6) 5 else 6
        var sssss = when (aa) {
            5, 7 -> ""
            6 -> {
                ""
            }
            else -> ""
        }

        var sss = when ("") {
            is String -> ""
            else
            -> ""
        }

        when (1) {
            is Int -> 1
            else
            -> 2
        }

        when {
            "" in "ss" -> ""
            else -> ""
        }


        var asssa = listOf<String>("", "")
        for (cds in asssa) {
            LogUtils.e("===", cds)
        }

        asssa.forEach {


        }

        var zz = AAA("")
        zz.cx
        zz.cc


    }

    open class Base {
        open fun f() {}
    }

    abstract class Derived {
        abstract fun f()
    }

    class cc : Derived() {
        override fun f() {

            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }


    class DD {
        inner  class SSS() {
            public fun foo() = 2
        }
    }

    open class Outer {                  // 外部类
        private val bar: Int = 1

        class Nested {             // 嵌套类
            fun foo() = 2
            fun cc() = 2
        }
    }

    open class ss constructor(ss:String):Outer(){

       open fun  sSSS(){}

    }

    interface  DD_D{
        fun ss(){
            print("")}
    }
    class AA<T>(t:T) {}

    class sddd:ss(""),DD_D{
        override fun ss() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun sSSS() {
            super.sSSS()
        }
    }

    fun main(args: Array) {
        DD().SSS().foo()
        val demo = Outer.Nested().cc() // 调用格式：外部类.嵌套类.嵌套类方法/属性
        println(demo)    // == 2
        var s=ss("ss");

    }


    //构造器
    class AAA constructor(a: String) {

        constructor(sss: String, ccc: String) : this(sss) {}

        var caa: String = ""

        init {
            this.caa = a
        }

        var cx: Int? = 1
        public fun get(): Int? {
            return cx;
        }

        var cc: Int = 1
            set
            get() {
                return 2
            }


    }


}

private operator fun <K, V> HashMap<K, V>.invoke(): Any {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
