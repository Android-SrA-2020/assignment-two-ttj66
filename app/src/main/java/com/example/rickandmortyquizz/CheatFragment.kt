package com.example.rickandmortyquizz


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.rickandmortyquizz.databinding.FragmentCheatBinding
import kotlinx.android.synthetic.main.fragment_cheat.*

/**
 * A simple [Fragment] subclass.
 */
class CheatFragment : Fragment() {
    lateinit var questionCheated: String
    var answerCheated: Boolean = false
    private lateinit var binding: FragmentCheatBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cheat, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          navController = view.findNavController();
       //  questionCheated = arguments!!.getString("question")?:"None"
        // answerCheated = arguments!!.getBoolean("answer")?: false
        // textView.text=questionCheated
        var args: CheatFragmentArgs= CheatFragmentArgs.fromBundle(arguments!!)
        questionCheated = args.question
        answerCheated = args.answer
        question_txt.text =questionCheated
        cancel_btn.setOnClickListener{
            activity!!.onBackPressed()
        }
        cheat_btn.setOnClickListener{
            checkAnswer( answerCheated)
        }
    }
    private fun checkAnswer( answ:Boolean){
        if(answ == true)
        {
            cheated_answer.setBackgroundResource(R.drawable.check_true)
        }else {
            cheated_answer.setBackgroundResource(R.drawable.check_false)
        }


    }

}
