package ir.mafiaaa.mafia;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.AlphaModifier;
import com.plattysoft.leonids.modifiers.ScaleModifier;
import com.richpath.RichPath;
import com.richpath.RichPathView;
import com.richpathanimator.AnimationListener;
import com.richpathanimator.RichPathAnimator;

import MAFIA.Util.FontsEnum;
import MAFIA.Util.MFonts;
import MAFIA.Game.Animation.SecretTextView;

public class ActivitySplashScreen extends AppCompatActivity {


    public void log(String log)
    {
        Log.d("FUUUCK" , log);
    }

    /**
     * for the first animations , "AAA" and "STUDIO" textViews
     */
    TextView tv_splash_aaa_logo;
    TextView tv_splash_aaa_studio;


    /**
     * these are for animating the 3 mafia men
     */
    RichPathView vec_splash_mafia_logo;
    RichPath[] vec_mafia_bg_paths , vec_mafia_white_paths;
    RichPath vec_mafia_main_black, vec_mafia_main_red;


    /**
     * these are for animating MAFIA header text
     */
    RichPathView vec_splash_mafia_header;
    RichPath vec_splash_mafia_header_letters[];
    RichPath vec_splash_mafia_header_rope ,vec_splash_mafia_header_snooze;


    /**
     * these are for animating the corner designs
     */
    RichPathView vec_splash_corner_left , vec_splash_corner_right;
    RichPath vec_splash_corner_left_path ,vec_splash_corner_right_path;

    /**
     * this is for background transitions and "ViewTreeObserver" class and
     * for animating smoke particles after "splash_layout" measurement
     */
    RelativeLayout splash_layout;



    /**
     * the "LOADING" text on the bottom
     */
    SecretTextView tv_loading;

    /**
     * these are viewEmitters and particle systems for drawing smoke effect on screen
     */
    View smoke_emitter_left ,smoke_emitter_center , smoke_emitter_right;
    ParticleSystem smoke_particles_left , smoke_particles_center ,smoke_particles_right;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        initViews();
        animate_AAA_LogoOnEnter();

    }


    /**
     * this function connect to server and load all data
     */
    private void loadDataFromServer()
    {
        releaseResources();

        //// TODO: 2/3/2018 implement this part

        tv_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TEMP_openAuthorizationActivity();
            }
        });


    }

    private void TEMP_openAuthorizationActivity()
    {

        startActivity(new Intent(this,ActivityAuthorization.class));
        overridePendingTransition(R.anim.anim_activity_transition_to_left_in,R.anim.anim_activity_transition_to_left_out);
        this.finish();
    }


    /**
     * this function animate the first part where the AAA Studio comes in
     * when this animation finished , it triggers the exiting animation
     */
    private void animate_AAA_LogoOnEnter()
    {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator anim_aaa_logo = ObjectAnimator.ofFloat(tv_splash_aaa_logo,"alpha",0,1).setDuration(1000);
        ObjectAnimator anim_aaa_studio = ObjectAnimator.ofFloat(tv_splash_aaa_studio,"alpha",0,1).setDuration(700);
        anim_aaa_studio.setStartDelay(150);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animate_AAA_LogoOnExit();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animatorSet.setStartDelay(500);
        animatorSet.playTogether(anim_aaa_logo,anim_aaa_studio);
        animatorSet.start();

        //--------------------------------- starting emitting smoke particles ---------------------------------
        initEmittingSmokeParticles();

    }


    /**
     * this function animate the exit effect of tha first animation and after that
     * it triggers the mafia men animation
     */
    private void animate_AAA_LogoOnExit()
    {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator anim_aaa_logo = ObjectAnimator.ofFloat(tv_splash_aaa_logo,"alpha",1,0).setDuration(800);
        ObjectAnimator anim_aaa_studio = ObjectAnimator.ofFloat(tv_splash_aaa_studio,"alpha",1,0).setDuration(600);
        //anim_aaa_studio.setStartDelay(150);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                tv_splash_aaa_studio.setVisibility(View.GONE);
                tv_splash_aaa_logo.setVisibility(View.GONE);
                tv_splash_aaa_studio = tv_splash_aaa_logo = null; //releasing resources
                animateMafiaVector();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animatorSet.setStartDelay(1000);
        animatorSet.playTogether(anim_aaa_logo,anim_aaa_studio);
        animatorSet.start();
    }


    private void initEmittingSmokeParticles()
    {
        smoke_particles_left = new ParticleSystem(this,2,R.drawable.smoke_particle_01,20000,R.id.splash_layout);
        smoke_particles_left.setInitialRotationRange(10,60);
        smoke_particles_left.setSpeedModuleAndAngleRange(-0.0048f,-0.0019f,90,10);
        smoke_particles_left.addModifier(new AlphaModifier(120,0,0,20000 , new DecelerateInterpolator()));
        smoke_particles_left.addModifier(new ScaleModifier(0.5f,3.5f,0,18000));


        smoke_particles_center = new ParticleSystem(this,6,R.drawable.smoke_particle_01,20000,R.id.splash_layout);
        smoke_particles_center.setAccelerationModuleAndAndAngleRange(-0.0000008f,-0.000002f,70,110);
        smoke_particles_center.setSpeedModuleAndAngleRange(-0.007f,-0.0014f,60,120);
        smoke_particles_center.addModifier(new AlphaModifier(80,0,0,20000 , new LinearInterpolator()));
        smoke_particles_center.addModifier(new ScaleModifier(0.6f,4.5f,0,16000));


//        smoke_particles_center = new ParticleSystem(this,4,R.drawable.smoke_particle_01,20000,R.id.splash_layout);
//        smoke_particles_center.setInitialRotationRange(10,60);
//        smoke_particles_center.setSpeedModuleAndAngleRange(-0.0048f,-0.0019f,40,140);
//        smoke_particles_center.addModifier(new AlphaModifier(120,0,0,20000 , new DecelerateInterpolator()));
//        smoke_particles_center.addModifier(new ScaleModifier(0.5f,3.5f,0,18000));



        smoke_particles_right = new ParticleSystem(this,2,R.drawable.smoke_particle_01,20000,R.id.splash_layout);
        smoke_particles_right.setInitialRotationRange(10,60);
        smoke_particles_right.setSpeedModuleAndAngleRange(-0.0048f,-0.0019f,90,160);
        smoke_particles_right.addModifier(new AlphaModifier(120,0,0,20000 , new DecelerateInterpolator()));
        smoke_particles_right.addModifier(new ScaleModifier(0.5f,4.5f,0,18000));


        ViewTreeObserver viewTreeObserver = splash_layout.getViewTreeObserver();
        if(viewTreeObserver.isAlive())
        {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    splash_layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    drawSmokeParticles();
                }
            });
        }

    }

    /**
     * this function must be called after parent layout measurement
     */
    private void drawSmokeParticles()
    {
        smoke_particles_left.oneShot(smoke_emitter_left,2);
        smoke_particles_center.oneShot(smoke_emitter_center,3);
        smoke_particles_right.oneShot(smoke_emitter_right,2);
    }



    private void animateMafiaVector()
    {

        //----------------------------------- animating the header text -----------------------------------
        RichPathAnimator.animate(vec_splash_mafia_header_letters)
                .strokeAlpha(0,1)
                .fillAlpha(0,1)
                .startDelay(100)
                .duration(800)

                .andAnimate(vec_splash_mafia_header_snooze , vec_splash_mafia_header_rope)
                .strokeAlpha(0,1)
                .fillAlpha(0,1)
                .startDelay(100)
                .duration(800)

                .thenAnimate(vec_splash_mafia_header_letters)
                .fillColor(Color.parseColor("#6d7c7d") , Color.parseColor("#FF9D0000") , Color.parseColor("#6d7c7d"))
                .strokeColor(Color.parseColor("#6d7c7d") , Color.parseColor("#FF9D0000") , Color.parseColor("#6d7c7d"))
                .duration(1500)
                .interpolator(new AccelerateDecelerateInterpolator())

                .andAnimate(vec_splash_mafia_header_snooze)
                .scaleX(1 , 0.6f , 1 )
                .repeatCount(RichPathAnimator.INFINITE)
                .interpolator(new LinearInterpolator())
                .duration(5000)

                .start();


        //----------------------------------- animating the mafia men ---------------------------------------
        RichPathAnimator
                .animate(vec_mafia_main_black).trimPathEnd(0,1).duration(1500).startDelay(100)
                .andAnimate(vec_mafia_main_red).trimPathEnd(0,1).duration(1500).startDelay(100)
                .andAnimate(vec_mafia_bg_paths).trimPathEnd(0,1).duration(1500).startDelay(100)
                .andAnimate(vec_mafia_white_paths).trimPathEnd(0,1).duration(1500).startDelay(100)

                .thenAnimate(vec_mafia_main_black).fillColor(Color.BLACK).duration(1000)
                .andAnimate(vec_mafia_main_red).fillColor(Color.RED).duration(1000)
                .andAnimate(vec_mafia_bg_paths).fillColor(Color.parseColor("#FF313131")).duration(1000)
                .andAnimate(vec_mafia_white_paths).fillColor(Color.parseColor("#9d9d9d")).duration(1000)
                .animationListener(new AnimationListener() {
                    @Override
                    public void onStart(){}

                    @Override
                    public void onStop()
                    {
                        tv_loading.setDuration(1500);
                        tv_loading.show();

                        animateCornerVectors();

                        ObjectAnimator anim_tv_loading = ObjectAnimator.ofFloat(tv_loading,"alpha",1 , 0 ,1);
                        anim_tv_loading.setRepeatCount(-1);
                        anim_tv_loading.setStartDelay(300);
                        anim_tv_loading.setDuration(3000);
                        anim_tv_loading.setInterpolator(new LinearInterpolator());
                        anim_tv_loading.start();

                        //-------------------------------- start listening to server and get data -------------------------------
                        loadDataFromServer();
                    }
                })

                .start();


        //----------------------------------- background transition effect -----------------------------------
        TransitionDrawable transition = (TransitionDrawable) splash_layout.getBackground();
        transition.startTransition(1300);
    }



    private void animateCornerVectors()
    {
        RichPathAnimator.animate(vec_splash_corner_left_path,vec_splash_corner_right_path)
                .fillAlpha(0 , 1)
                .duration(1800)
                .startDelay(100)
                .interpolator(new DecelerateInterpolator())
                .start();

    }

    private void initViews()
    {
        //----------------------------------- text views ----------------------------------------
        tv_splash_aaa_logo = (TextView) findViewById(R.id.tv_splash_AAA_logo);
        tv_splash_aaa_logo.setTypeface(MFonts.getFont(this, FontsEnum.splashScreen_AAA_logo));

        tv_splash_aaa_studio = (TextView) findViewById(R.id.tv_splash_AAA_studio);
        tv_splash_aaa_studio.setTypeface(MFonts.getFont(this,FontsEnum.splashScreen_AAA_studio));

        tv_loading = (SecretTextView) findViewById(R.id.tv_splash_loading);
        tv_loading.setTypeface(MFonts.getFont(this , FontsEnum.splashScreen_AAA_Loading));


        splash_layout = (RelativeLayout) findViewById(R.id.splash_layout);

        //----------------------------- smoke particle emitters ----------------------------------
        smoke_emitter_left = findViewById(R.id.smoke_emitter_left);
        smoke_emitter_center = findViewById(R.id.smoke_emitter_center);
        smoke_emitter_right = findViewById(R.id.smoke_emitter_right);



        //---------------------------------------- corner vectors ----------------------------------
        vec_splash_corner_left = (RichPathView) findViewById(R.id.rp_splash_corner_bottom_left);
        vec_splash_corner_left_path = vec_splash_corner_left.findRichPathByIndex(0);
        vec_splash_corner_left_path.setFillAlpha(0);

        vec_splash_corner_right = (RichPathView) findViewById(R.id.rp_splash_corner_bottom_right);
        vec_splash_corner_right_path = vec_splash_corner_right.findRichPathByIndex(0);
        vec_splash_corner_right_path.setFillAlpha(0);



        //---------------------------------mafia vector logo ---------------------------------------
        vec_splash_mafia_logo = (RichPathView) findViewById(R.id.rp_splash_mafia_vec);
        vec_mafia_bg_paths = new RichPath[7];
        for (int i = 0; i < 7 ; i++)
        {
            vec_mafia_bg_paths[i] = vec_splash_mafia_logo.findRichPathByName("bg" + i);
            vec_mafia_bg_paths[i].setFillColor(Color.TRANSPARENT);
            vec_mafia_bg_paths[i].setStrokeWidth(0.4f);
            vec_mafia_bg_paths[i].setStrokeColor(Color.BLACK);
            vec_mafia_bg_paths[i].setTrimPathEnd(0);
        }

        vec_mafia_white_paths = new RichPath[10];
        for (int i = 0; i < 10 ; i++)
        {
            vec_mafia_white_paths[i] = vec_splash_mafia_logo.findRichPathByName("w" + i);
            vec_mafia_white_paths[i].setFillColor(Color.TRANSPARENT);
            vec_mafia_white_paths[i].setStrokeWidth(0.4f);
            vec_mafia_white_paths[i].setStrokeColor(Color.WHITE);
            vec_mafia_white_paths[i].setTrimPathEnd(0);
        }

        vec_mafia_main_black = vec_splash_mafia_logo.findRichPathByName("main_black");
        vec_mafia_main_black.setFillColor(Color.TRANSPARENT);
        vec_mafia_main_black.setStrokeWidth(0.4f);
        vec_mafia_main_black.setStrokeColor(Color.BLACK);
        vec_mafia_main_black.setTrimPathEnd(0);

        vec_mafia_main_red = vec_splash_mafia_logo.findRichPathByName("main_red");
        vec_mafia_main_red.setFillColor(Color.TRANSPARENT);
        vec_mafia_main_red.setStrokeWidth(0.4f);
        vec_mafia_main_red.setStrokeColor(Color.RED);
        vec_mafia_main_red.setTrimPathEnd(0);


        //--------------------------- mafia header text vector --------------------------------------
        vec_splash_mafia_header = (RichPathView) findViewById(R.id.rp_splash_mafia_header_vec);
        vec_splash_mafia_header_letters = new RichPath[5];
        for (int i = 0 ; i < 5 ; i++)
        {
            vec_splash_mafia_header_letters[i] = vec_splash_mafia_header.findRichPathByIndex(i);
            vec_splash_mafia_header_letters[i].setFillAlpha(0);
            vec_splash_mafia_header_letters[i].setStrokeAlpha(0);
        }

        vec_splash_mafia_header_rope = vec_splash_mafia_header.findRichPathByName("rope");
        vec_splash_mafia_header_rope.setStrokeAlpha(0);
        vec_splash_mafia_header_rope.setPivotToCenter(true);


        vec_splash_mafia_header_snooze = vec_splash_mafia_header.findRichPathByName("snooze");
        vec_splash_mafia_header_snooze.setStrokeAlpha(0);
        vec_splash_mafia_header_snooze.setPivotToCenter(true);

    }


    private void releaseResources()
    {
        tv_splash_aaa_logo = null;
        tv_splash_aaa_studio = null;

        vec_mafia_bg_paths = null;
        vec_mafia_white_paths = null;

        vec_mafia_main_black = null;
        vec_mafia_main_red = null;


        vec_splash_mafia_header_letters = null;
        vec_splash_mafia_header_rope = null;
        vec_splash_mafia_header_snooze = null;

        vec_splash_corner_left_path = null;
        vec_splash_corner_right_path = null;


        smoke_emitter_left = null;
        smoke_emitter_center = null;
        smoke_emitter_right = null;

        smoke_particles_left = null;
        smoke_particles_center = null;
        smoke_particles_right = null;
    }
}
