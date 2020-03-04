package com.example.rickandmortyquizz


import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.rickandmortyquizz.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController
    data class Quizz(
        val text:String,
        val answer:Boolean
    )
    private val quizzes: MutableList<Quizz> = mutableListOf(
        Quizz(text="Eritrea, which became the 182nd member of the UN in 1993, is in the continent of Africa",
            answer =true
        ),
        Quizz(text="Entomology is the science that studies Insects",
            answer = false
        ),
        Quizz(text="Nobel Prize awarded recompense research in Physics and Chemistry? ",
            answer = true
        ),
        Quizz(text="Hitler party which came into power in 1933 is known as Nazi Party",
            answer = true
        ),
        Quizz(text="FFC stands for Federation of Football Council",
            answer = false
        ),
        Quizz(text="First human heart transplant operation conducted by Dr. Christiaan Barnard on Louis Washkansky, was conducted in 1922",
            answer = false
        ),
        Quizz(text="Galileo was an Italian astronomer who developed the telescope",
            answer = true
        ),
        Quizz(text="Habeas Corpus Act 1679 safeguarded the personal liberties of the people against arbitrary imprisonment by the king's orders",
            answer = true
        ),
        Quizz(text="First China War was fought between China and Britain",
            answer = true
        ),
        Quizz(text="Exposure to sunlight helps a person improve his health because resistance power increases",
            answer = false
        )

    )
    lateinit var currentQuestion: Quizz
    private var quizzIndex = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        randomizeQuestions()
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navController.navigate(R.id.action_mainFragment_to_aboutFragment)
        return NavigationUI.onNavDestinationSelected(item, navController)|| super.onOptionsItemSelected(item)
    }
    private fun setQuestion(){
        currentQuestion = quizzes[quizzIndex]

    }
    private fun randomizeQuestions() {
        quizzes.shuffle()
        quizzIndex = 0
        setQuestion()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        quizzIndex= 0
        updateView()
        binding.apply {
            cheatBtn.setOnClickListener {
                if (!TextUtils.isEmpty(question_txt.text.toString())) {
                    //  val bundle = bundleOf("question" to question_text.text.toString(),
                    //                              "answer" to quizzes[quizzIndex].answer   )
                   navController.navigate(
                        MainFragmentDirections.actionMainFragmentToCheatFragment(
                           question_txt.text.toString(),
                          quizzes[quizzIndex].answer
                       )
                   )
                } else {
                    val toast =
                        Toast.makeText(context, "No question was selected", Toast.LENGTH_SHORT)
                    toast.show()
                }

            }
        }
   
    move_fwd_btn.setOnClickListener{
        if(quizzIndex < quizzes.size - 1)
        {
            quizzIndex++
        }else{
            quizzIndex= 0
        }
        updateView()

    }
    move_back_btn.setOnClickListener{
        if(quizzIndex > 0)
        {
            quizzIndex--
        }else{
            quizzIndex= quizzes.size - 1
        }
        updateView()

    }
    answer_false_btn.setOnClickListener{
        checkAnswer(false)
    }
    answer_true_btn.setOnClickListener{
        checkAnswer(true)
    }

}

private fun updateView(){
    check_answer_txt.setBackgroundResource(R.drawable.ic_launcher_foreground)
    question_txt.setText(quizzes[quizzIndex].text)
}
private fun checkAnswer( answ:Boolean){
    if(answ== quizzes[quizzIndex].answer)
    {
        check_answer_txt.setBackgroundResource(R.drawable.check_true)
    }else {
        check_answer_txt.setBackgroundResource(R.drawable.check_false)
    }

}
}


