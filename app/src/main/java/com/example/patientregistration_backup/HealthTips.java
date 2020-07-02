package com.example.patientregistration_backup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class HealthTips extends AppCompatActivity {


    TextView textView;

    private String jokes[] = {"Learn to do stretching \nexercises when you \nwake up",
            " Garlic, onions, spring onions and \nleeks all contain \nstuff that’s good \nfor you.",
            "Tomato is a \nsuperstar in the \nfruit and veggie \npantheon",
            "Prevent low blood sugar as it stresses \nyou out",
            "We need at least 90 \nmg of vitamin C \nper day",
            "Folic acid should \nbe taken regularly \nby all pregnant mums",
            "This vitamin, \nand beta carotene, \nhelp to boost \nimmunity against \ndisease",
            "Don’t have soft \ndrinks or energy \ndrinks while you're \nexercising",
            "Stay properly \nhydrated by \ndrinking enough \nwater during \nyour workout ",
            "Breathe deeply \nto help your \nbody move \noxygen-rich \nblood to those \nsore muscles",
            "Experts say \nweight training \nshould be done \nfirst",
            "To improve your \nfitness harness the \njoys of interval \ntraining",
            "Set the treadmill \nor step machine \non the interval \nprogramme",
            "Don’t eat \ncarbohydrates for \nat least an \nhour after exercise",
            "Don’t smoke and \nif you smoke \nalready, do \neverything to quit",
            "Do regular \nself-examinations \nof your breasts",
            "Install an air filter \nin your home.",
            "Keep your pet \noutside as much \nas possible and \nbrush him",
            "Swimming is the \nmost asthma -friendly \nsport",
            "Asthma need not \nhinder peak performance \nin sport",
            "Stay away from \nperfumed or \nflavoured suntan \nlotions",
            "Sunscreen is \nunlikely to stop \nyou from being \nsunburned",
            "Laughter help \nheal bodies, \nas well as broken \nhearts",
            "Laughter boosts \nthe immune \nsystem",
            "Warm water is \nbest for showers",
            "Apply moisturiser \nwhile your skin \nis still damp ",
            "Training for as \nlittle as 5 minutes \ncan do wonders",
            "A cardio workout \nin morning \nstarts your \nworkday with energy",
            "Start with just \n10 minutes of \ncardio workout in \nthe morning",
            "Do 10-15 minutes \nof body workout at \nhome three times \nper week",
            "Do yoga before or \nafter work",
            "Walk to the office",
            "Park far from \nyour office",
            "Take the stairs",
            "Consider a \nstanding desk",
            "Join a gym",
            "Do interval training",
            "Sign yourself up \nfor gym classes",
            "Find a quiet place \nto relax",
            "Stretches reduce \nstress",
            "Reduce caffeine",
            "Drink green tea",
            "Add magnesium \nrich foods to \nyour diet",
            "Start meditating",
            "Get enough sleep",
            "Keep your \nbedroom dark",
            "Meditate before \nbed to calm \nyour mind",
            "Eat real fruit. \nLimit Juice",
            "Reduce or \navoid alcohol \nintake",
            "Include vegetables \nin every meal",
            "Eat Salads",
            "Have healthy \nsnacks at hand",
            "Always eat \nmindfully",
            "Drink a lot of \nwater",
            "Quit Smoking"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);


        textView = (TextView)findViewById(R.id.textView);


        Random random = new Random();
        int num = random.nextInt(jokes.length);
        textView.setText(jokes[num]);



    }
}