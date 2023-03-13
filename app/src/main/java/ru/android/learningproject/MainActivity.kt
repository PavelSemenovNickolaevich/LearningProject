package ru.android.learningproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.android.learningproject.constance.Constance
import ru.android.learningproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var bindingClass: ActivityMainBinding
    private var login: String = "empty"
    private var password: String = "empty"
    private var name: String = "empty"
    private var name2: String = "empty"
    private var name3: String = "empty"
    private var avatarImageUD: Int = 0
    val lostArray = arrayOf(10000, 2300, 45000, 65000, 6500, 400)
    val earnArray = arrayOf(15000, 300, 345000, 5000, 16500, 3400)
    val resultArray = ArrayList<String>()

    val bad = 0..3
    val normal = 4..6
    val nice = 7..9
    val excellent = 10
    val gradeArray = arrayOf(4, 7, 3, 6, 10, 2)
    val nameArray = arrayOf("Антон", "Егор", "Маша", "Светлана", "Юля", "Семен")
    val badArray = ArrayList<String>()
    val normalArray = ArrayList<String>()
    val niceArray = ArrayList<String>()
    val excellentArray = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        val names = resources.getStringArray(R.array.names)

        for ((index, name) in names.withIndex()) {

            resultArray.add("Имя: $name - прибыль = ${earnArray[index] - lostArray[index]}")
            Log.d("My log", "Статистика - / - ${resultArray[index]}")
        }

        for ((index, name) in nameArray.withIndex()) {
            when (gradeArray[index]) {
                in bad -> badArray.add("Плохие оценки: Ученик $name - ${gradeArray[index]}")
                in normal -> normalArray.add("Нормальные оценки: Ученик $name - ${gradeArray[index]}")
                in nice -> niceArray.add("Хорошие оценки: Ученик $name - ${gradeArray[index]}")
                excellent -> excellentArray.add("Отличные оценки: Ученик $name - ${gradeArray[index]}")
            }
        }

        badArray.forEach{Log.d("MyLog", it)}
        normalArray.forEach{Log.d("MyLog", it)}
        niceArray.forEach{Log.d("MyLog", it)}
        excellentArray.forEach{Log.d("MyLog", it)}
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constance.REQUEST_CODE_SIGN_IN) {
            val l = data?.getStringExtra(Constance.LOGIN)
            val p = data?.getStringExtra(Constance.PASSWORD)
            if (login == l && password == p) {
                bindingClass.imAvatar.setImageResource(avatarImageUD)
                val textInfo = "$name $name2 $name3 "
                bindingClass.tvinfo.text = textInfo
                bindingClass.bHide.visibility = View.GONE
                bindingClass.bExit.text = "Выйти"
            } else {
                bindingClass.tvinfo.text = "Такого аккаунта не сущетвует!!"
            }

        } else if (requestCode == Constance.REQUEST_CODE_SIGN_UP) {

            login = data?.getStringExtra(Constance.LOGIN)!!
            password = data.getStringExtra(Constance.PASSWORD)!!
            name = data.getStringExtra(Constance.NAME)!!
            name2 = data.getStringExtra(Constance.NAME2)!!
            name3 = data.getStringExtra(Constance.NAME3)!!
            val textInfo = "$name $name2 $name3 "
            bindingClass.tvinfo.text = textInfo
            bindingClass.bHide.visibility = View.GONE
            bindingClass.bExit.text = "Выйти"

        }
    }

    fun onClickSignIn(view: View) {

        val intent = Intent(this, SignInUpActivity::class.java)
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
        startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_IN)

    }

    fun onClickSignUp(view: View) {

        val intent = Intent(this, SignInUpActivity::class.java)
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP_STATE)
        startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_UP)

    }
}