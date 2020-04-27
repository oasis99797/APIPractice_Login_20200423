package kr.tjeit.apipractice_login_20200423

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kr.tjeit.apipractice_login_20200423.utils.ServerUtil
import org.json.JSONObject

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        loginBtn.setOnClickListener {
            val inputId = idEdt.text.toString() // cho881020 => 조경진
            val inputPw = pwEdt.text.toString() // asdf1234

//            서버로 로그인 요청 => ServerUtil클래스 기능 활용
            ServerUtil.postRequestLogin(mContext, inputId, inputPw, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

//                    실제로 응답을 받은걸 분석해서 => 대응

//                    임시로 서버 응답 확인 하기 위한 코드
                    Log.d("서버응답JSON", json.toString())

                    val code = json.getInt("code")

                    if (code == 200) {
//                        로그인 성공

                        val data = json.getJSONObject("data")
                        val user = data.getJSONObject("user")
                        val name = user.getString("name")

                        val myIntent = Intent(mContext, MainActivity::class.java)
                        myIntent.putExtra("userName", name)
                        startActivity(myIntent)

                    }
                    else {
                        val message = json.getString("message")

                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }

                    }

                }

            })

        }

    }

    override fun setValues() {

    }

}
