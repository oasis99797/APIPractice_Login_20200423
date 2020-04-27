package kr.tjeit.apipractice_login_20200423

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kr.tjeit.apipractice_login_20200423.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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


                }

            })

        }

    }

    override fun setValues() {

    }

}
