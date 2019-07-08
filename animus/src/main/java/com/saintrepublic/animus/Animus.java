package com.saintrepublic.animus;

/*
 * Copyright 2019 SaintRepublic
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.support.annotation.IntRange;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class Animus {

    /*
     * Animus - is a simple library to simplify the views animation.
     * Version: 1.2
     *
     * Developed by SaintRepublic.
     *
     * You can get sources and samples of this library
     * on GitHub:
     * https://github.com/SaintRepublic/Animus
     */

    //=====================================           ==============================================
    //==================================== Static part =============================================
    //=====================================           ==============================================

    // Common interpolator for animations
    private static Interpolator cInterpolator = new LinearInterpolator();
    // Common AnimationListener for animations
    private static Animation.AnimationListener cListener;
    // Common fillBefore parameter for animations
    private static boolean isFillBefore = true;

    /**
     * Types of animations interpolator
     */
    public static class Interpolation {
        public final static int ACCELERATE = 0;
        public final static int DECELERATE = 1;
        public final static int ACCELERATE_DECELERATE = 2;
        public final static int ANTICIPATE = 3;
        public final static int OVERSHOOT = 4;
        public final static int ANTICIPATE_OVERSHOOT = 5;
        public final static int BOUNCE = 6;
        public final static int CYCLE = 7;
        public final static int LINEAR = 8;
        public final static int FASTOUT_LINEARIN = 9;
        public final static int FASTOUT_SLOWIN = 10;
        public final static int LINEAROUT_SLOWIN = 11;
    }

    /**
     * Get new instance of Interpolator
     *
     * @param interpolatorType Interpolator type
     * @return new Interpolator instance
     */
    public static Interpolator getNewInterpolator(@IntRange(from=0,to=11) int interpolatorType) {
        switch (interpolatorType) {
            case 0: { return new AccelerateInterpolator();}
            case 1: { return new DecelerateInterpolator();}
            case 2: { return new AccelerateDecelerateInterpolator();}
            case 3: { return new AnticipateInterpolator();}
            case 4: { return new OvershootInterpolator();}
            case 5: { return new AnticipateOvershootInterpolator();}
            case 6: { return new BounceInterpolator();}
            case 7: { return new CycleInterpolator(1);}
            case 8: { return new LinearInterpolator();}
            case 9: { return new FastOutLinearInInterpolator();}
            case 10: { return new FastOutSlowInInterpolator();}
            case 11: { return new LinearOutSlowInInterpolator();}
            default: { return new LinearInterpolator();}
        }
    }

    /**
     * Set default animations interpolator
     *
     * @param interpolatorType Type of interpolator
     */
    public static void setCommonInterpolator(@IntRange(from=0,to=11) int interpolatorType) {
        cInterpolator = getNewInterpolator(interpolatorType);
    }

    /**
     * Set default animations interpolator
     *
     * @param interpolator Common interpolator
     */
    public static void setCommonInterpolator(Interpolator interpolator) {
        cInterpolator = interpolator;
    }

    /**
     * Returns current common animation interpolator
     *
     * @return Current common interpolator
     */
    public static Interpolator getCommonInterpolator() {
        return cInterpolator;
    }

    /**
     * Set default listener for all animations
     *
     * @param listener Common AnimationListener for animations
     */
    public static void setCommonAnimationListener(Animation.AnimationListener listener) {
        cListener = listener;
    }

    /**
     * Returns current common AnimationListener
     *
     * @return Current common AnimationListener
     */
    public static Animation.AnimationListener getCommonAnimationListener() {
        return cListener;
    }

    /**
     * Set parameter fillBefore for all animations
     *
     * @param fillBefore  Common fillBefore parameter for animations
     */
    public static void setCommonFillBefore(boolean fillBefore) {
        isFillBefore = fillBefore;
    }

    /**
     * Returns current common FillBefore parameter
     *
     * @return Current common FillBefore value
     */
    public static boolean isCommonFillBefore() {
        return isFillBefore;
    }

    private static Animation setDefaults(Animation animation, int duration, boolean fillAfter) {
        animation.setDuration(duration);
        animation.setFillAfter(fillAfter);
        animation.setFillBefore(isFillBefore);
        animation.setFillEnabled(isFillBefore);
        animation.setInterpolator(cInterpolator);
        animation.setAnimationListener(cListener);
        return animation;
    }

    public static class Move {

        public static class To {

            /**
             * Animate moving to the left of self
             *
             * @param duration duration in milliseconds
             * @param fillAfter true if the animation should apply its transformation after it ends
             * @return TranslateAnimation
             */
            static public Animation left(int duration, boolean fillAfter){
                Animation animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, -1.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f);

                return setDefaults(animation, duration, fillAfter);
            }

            /**
             * Animate moving to the right of self
             *
             * @param duration duration in milliseconds
             * @param fillAfter true if the animation should apply its transformation after it ends
             * @return TranslateAnimation
             */
            static public Animation right(int duration, boolean fillAfter){
                Animation animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 1.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f);

                return setDefaults(animation, duration, fillAfter);
            }

            /**
             * Animate moving to the top of self
             *
             * @param duration duration in milliseconds
             * @param fillAfter true if the animation should apply its transformation after it ends
             * @return TranslateAnimation
             */
            static public Animation top(int duration, boolean fillAfter){
                Animation animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, -1.0f);

                return setDefaults(animation, duration, fillAfter);
            }

            /**
             * Animate moving to the bottom of self
             *
             * @param duration duration in milliseconds
             * @param fillAfter true if the animation should apply its transformation after it ends
             * @return TranslateAnimation
             */
            static public Animation bottom(int duration, boolean fillAfter){
                Animation animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 1.0f);

                return setDefaults(animation, duration, fillAfter);
            }

            public static class Parent {

                /**
                 * Animate moving beyond the left bound of parent layout
                 *
                 * @param duration duration in milliseconds
                 * @param fillAfter true if the animation should apply its transformation after it ends
                 * @return TranslateAnimation
                 */
                static public Animation left(int duration, boolean fillAfter){
                    Animation animation = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_PARENT, -1.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f);

                    return setDefaults(animation, duration, fillAfter);
                }

                /**
                 * Animate moving beyond the right bound of parent layout
                 *
                 * @param duration duration in milliseconds
                 * @param fillAfter true if the animation should apply its transformation after it ends
                 * @return TranslateAnimation
                 */
                static public Animation right(int duration, boolean fillAfter){
                    Animation animation = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_PARENT, 1.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f);

                    return setDefaults(animation, duration, fillAfter);
                }

                /**
                 * Animate moving beyond the top bound of parent layout
                 *
                 * @param duration duration in milliseconds
                 * @param fillAfter true if the animation should apply its transformation after it ends
                 * @return TranslateAnimation
                 */
                static public Animation top(int duration, boolean fillAfter){
                    Animation animation = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_PARENT, -1.0f);

                    return setDefaults(animation, duration, fillAfter);
                }


                /**
                 * Animate moving beyond the bottom bound of parent layout
                 *
                 * @param duration duration in milliseconds
                 * @param fillAfter true if the animation should apply its transformation after it ends
                 * @return TranslateAnimation
                 */
                static public Animation bottom(int duration, boolean fillAfter){
                    Animation animation = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_PARENT, 1.0f);

                    return setDefaults(animation, duration, fillAfter);
                }
            }
        }

        public static class From {

            /**
             * Animate moving from the left of self to default position
             *
             * @param duration duration in milliseconds
             * @param fillAfter true if the animation should apply its transformation after it ends
             * @return TranslateAnimation
             */
            static public Animation left(int duration, boolean fillAfter){
                Animation animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, -1.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f);

                return setDefaults(animation, duration, fillAfter);
            }

            /**
             * Animate moving from the right of self to default position
             *
             * @param duration duration in milliseconds
             * @param fillAfter true if the animation should apply its transformation after it ends
             * @return TranslateAnimation
             */
            static public Animation right(int duration, boolean fillAfter){
                Animation animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 1.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f);

                return setDefaults(animation, duration, fillAfter);
            }

            /**
             * Animate moving from the top of self to default position
             *
             * @param duration duration in milliseconds
             * @param fillAfter true if the animation should apply its transformation after it ends
             * @return TranslateAnimation
             */
            static public Animation top(int duration, boolean fillAfter){
                Animation animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, -1.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f);

                return setDefaults(animation, duration, fillAfter);
            }

            /**
             * Animate moving from the bottom of self to default position
             *
             * @param duration duration in milliseconds
             * @param fillAfter true if the animation should apply its transformation after it ends
             * @return TranslateAnimation
             */
            static public Animation bottom(int duration, boolean fillAfter){
                Animation animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 1.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f);

                return setDefaults(animation, duration, fillAfter);
            }

            public static class Parent {

                /**
                 * Animate moving from left of parent to default position
                 *
                 * @param duration duration in milliseconds
                 * @param fillAfter true if the animation should apply its transformation after it ends
                 * @return TranslateAnimation
                 */
                static public Animation left(int duration, boolean fillAfter){
                    Animation animation = new TranslateAnimation(
                            Animation.RELATIVE_TO_PARENT, -1.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f);

                    return setDefaults(animation, duration, fillAfter);
                }

                /**
                 * Animate moving from right of parent to default position
                 *
                 * @param duration duration in milliseconds
                 * @param fillAfter true if the animation should apply its transformation after it ends
                 * @return TranslateAnimation
                 */
                static public Animation right(int duration, boolean fillAfter){
                    Animation animation = new TranslateAnimation(
                            Animation.RELATIVE_TO_PARENT, 1.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f);

                    return setDefaults(animation, duration, fillAfter);
                }

                /**
                 * Animate moving from top of parent to default position
                 *
                 * @param duration duration in milliseconds
                 * @param fillAfter true if the animation should apply its transformation after it ends
                 * @return TranslateAnimation
                 */
                static public Animation top(int duration, boolean fillAfter){
                    Animation animation = new TranslateAnimation(
                            Animation.RELATIVE_TO_PARENT, 0.0f,
                            Animation.RELATIVE_TO_PARENT, 0.0f,
                            Animation.RELATIVE_TO_PARENT, -1.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f);

                    return setDefaults(animation, duration, fillAfter);
                }

                /**
                 * Animate moving from bottom of parent to default position
                 *
                 * @param duration duration in milliseconds
                 * @param fillAfter true if the animation should apply its transformation after it ends
                 * @return TranslateAnimation
                 */
                static public Animation bottom(int duration, boolean fillAfter){
                    Animation animation = new TranslateAnimation(
                            Animation.RELATIVE_TO_PARENT, 0.0f,
                            Animation.RELATIVE_TO_PARENT, 0.0f,
                            Animation.RELATIVE_TO_PARENT, 1.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f);

                    return setDefaults(animation, duration, fillAfter);
                }
            }
        }

        /**
         * Animate moving with custom parameters relative to self
         *
         * @param fx staring horizontal factor
         * @param tx ending horizontal factor
         * @param fy starting vertical factor
         * @param ty ending vertical factor
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        static public Animation move(float fx, float tx, float fy, float ty, int duration, boolean fillAfter) {
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, fx,
                    Animation.RELATIVE_TO_SELF, tx,
                    Animation.RELATIVE_TO_SELF, fy,
                    Animation.RELATIVE_TO_SELF, ty);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving with custom parameters relative to parent layout
         *
         * @param fx staring horizontal factor
         * @param tx ending horizontal factor
         * @param fy starting vertical factor
         * @param ty ending vertical factor
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        static public Animation moveToParent(float fx, float tx, float fy, float ty, int duration, boolean fillAfter) {
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, fx,
                    Animation.RELATIVE_TO_PARENT, tx,
                    Animation.RELATIVE_TO_PARENT, fy,
                    Animation.RELATIVE_TO_PARENT, ty);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving with custom parameters
         * relative to self at start of animation
         * relative to parent at the end of animation
         *
         * @param fromSelfX staring horizontal factor relative to self
         * @param toParentX ending horizontal factor relative to parent
         * @param fromSelfY starting vertical factor relative to self
         * @param toParentY ending vertical factor relative to parent
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        static public Animation moveFromSelfToParent(float fromSelfX, float toParentX, float fromSelfY, float toParentY, int duration, boolean fillAfter) {
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, fromSelfX,
                    Animation.RELATIVE_TO_PARENT, toParentX,
                    Animation.RELATIVE_TO_SELF, fromSelfY,
                    Animation.RELATIVE_TO_PARENT, toParentY);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving with custom parameters
         * relative to parent at start of animation
         * relative to self at the end of animation
         *
         * @param fromParentX staring horizontal factor relative to parent
         * @param toSelfX ending horizontal factor relative to self
         * @param fromParentY starting vertical factor relative to parent
         * @param toSelfY ending vertical factor relative to self
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        static public Animation moveFromParentToSelf(float fromParentX, float toSelfX, float fromParentY, float toSelfY, int duration, boolean fillAfter) {
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, fromParentX,
                    Animation.RELATIVE_TO_SELF, toSelfX,
                    Animation.RELATIVE_TO_PARENT, fromParentY,
                    Animation.RELATIVE_TO_SELF, toSelfY);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving with custom parameters
         *
         * @param fx staring horizontal factor
         * @param fxRelation specifies how fx should be interpreted. One of
         *        Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, or Animation.RELATIVE_TO_PARENT.
         * @param tx ending horizontal factor
         * @param txRelation specifies how tx should be interpreted. One of
         *        Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, or Animation.RELATIVE_TO_PARENT.
         * @param fy starting vertical factor
         * @param fyRelation specifies how fy should be interpreted. One of
         *        Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, or Animation.RELATIVE_TO_PARENT.
         * @param ty ending vertical factor
         * @param tyRelation specifies how ty should be interpreted. One of
         *        Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, or Animation.RELATIVE_TO_PARENT.
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        static public Animation custom(float fx, int fxRelation, float tx, int txRelation,
                                       float fy, int fyRelation, float ty, int tyRelation, int duration, boolean fillAfter) {

            Animation animation = new TranslateAnimation(fxRelation, fx, txRelation, tx, fyRelation, fy, tyRelation, ty);
            return setDefaults(animation, duration, fillAfter);
        }
    }

    public static class Alpha {

        /**
         * Animate visibility changing from visible to invisible
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return Alpha animation
         */
        static public Animation hide(int duration, boolean fillAfter){
            Animation animation = new AlphaAnimation(1.0f, 0.0f);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate visibility changing from invisible to visible
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return Alpha animation
         */
        static public Animation show(int duration, boolean fillAfter){
            Animation animation = new AlphaAnimation(0.0f, 1.0f);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate opacity changing with custom parameters
         *
         * @param startingOpacity opacity at the start of animation
         * @param endingOpacity opacity at the end of animation
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return AlphaAnimation
         */
        static public Animation alpha(int startingOpacity, int endingOpacity, int duration, boolean fillAfter){
            Animation animation = new AlphaAnimation(startingOpacity, endingOpacity);
            return setDefaults(animation, duration, fillAfter);
        }
    }

    public static class Scale {

        /**
         * Animate scale with custom parameters relative to self
         *
         * @param fx horizontal factor at start of animation
         * @param tx horizontal factor at the end
         * @param fy vertical factor at start of animation
         * @param ty vertical factor at the and
         * @param pivotX pivot horizontal position
         * @param pivotY pivot vertical position
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return ScaleAnimation
         */
        static public Animation scale(float fx, float tx, float fy, float ty, float pivotX, float pivotY, int duration, boolean fillAfter) {
            Animation animation = new ScaleAnimation(fx, tx, fy, ty, Animation.RELATIVE_TO_SELF, pivotX, Animation.RELATIVE_TO_SELF, pivotY);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate scale from 0 to default size
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return ScaleAnimation
         */
        static public Animation from0to1(int duration, boolean fillAfter) {
            Animation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate scale from default size to 0
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return ScaleAnimation
         */
        static public Animation from1to0(int duration, boolean fillAfter) {
            Animation animation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
                    Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate scale from default size double size
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return ScaleAnimation
         */
        static public Animation from1to2(int duration, boolean fillAfter) {
            Animation animation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
                    Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate scale from double size to default size
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return ScaleAnimation
         */
        static public Animation from2to1(int duration, boolean fillAfter) {
            Animation animation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
                    Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate scale with custom parameters
         *
         * @param fx horizontal factor at start of animation
         * @param tx horizontal factor at the end
         * @param fy vertical factor at start of animation
         * @param ty vertical factor at the and
         * @param pivotX pivot horizontal position
         * @param pivotXRelation specifies how pivotX should be interpreted. One of
         *        Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF or Animation.RELATIVE_TO_PARENT
         * @param pivotY pivot vertical position
         * @param pivotYRelation specifies how pivotY should be interpreted. One of
         *        Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF or Animation.RELATIVE_TO_PARENT
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return ScaleAnimation
         */
        static public Animation custom(float fx, float tx, float fy, float ty, float pivotX, int pivotXRelation, float pivotY, int pivotYRelation, int duration, boolean fillAfter) {
            Animation animation = new ScaleAnimation(fx, tx, fy, ty, pivotXRelation, pivotX, pivotYRelation, pivotY);
            return setDefaults(animation, duration, fillAfter);
        }
    }

    public static class Rotate {

        /**
         * Animate rotation with custom parameters with pivot relative to self
         *
         * @param fd starting degrees
         * @param td ending degrees
         * @param pivotX horizontal pivot position relative to self
         * @param pivotY vertical pivot position relative to self
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return RotateAnimation
         */
        static public Animation relativeToSelf(float fd, float td, float pivotX, float pivotY, int duration, boolean fillAfter) {
            Animation animation = new RotateAnimation(fd, td, Animation.RELATIVE_TO_SELF, pivotX, Animation.RELATIVE_TO_SELF, pivotY);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate rotation with custom parameters with pivot relative to parent layout
         *
         * @param fd starting degrees
         * @param td ending degrees
         * @param pivotX horizontal pivot position relative to parent
         * @param pivotY vertical pivot position relative to parent
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return RotateAnimation
         */
        static public Animation relativeToParent(float fd, float td, float pivotX, float pivotY, int duration, boolean fillAfter) {
            Animation animation =  new RotateAnimation(fd, td, Animation.RELATIVE_TO_PARENT, pivotX, Animation.RELATIVE_TO_PARENT, pivotY);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate rotation with custom parameters
         *
         * @param fd starting degrees
         * @param td ending degrees
         * @param pivotX horizontal pivot position relative to self
         * @param pivotXRelation specifies how pivotX should be interpreted. One of
         *        Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF or Animation.RELATIVE_TO_PARENT
         * @param pivotY vertical pivot position relative to self
         * @param pivotYRelation specifies how pivotY should be interpreted. One of
         *        Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF or Animation.RELATIVE_TO_PARENT
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return RotateAnimation
         */
        static public Animation custom(float fd, float td, float pivotX, int pivotXRelation,
                                       float pivotY, int pivotYRelation, int duration, boolean fillAfter) {
            Animation animation =  new RotateAnimation(fd, td, pivotXRelation, pivotX, pivotYRelation, pivotY);
            return setDefaults(animation, duration, fillAfter);
        }
    }

    //===================================                 ==========================================
    //================================== Non-static Animus =========================================
    //===================================                 ==========================================

    /**
     * Non-static instance of Animus
     */
    public static class Instance {

        // Common interpolator for animations
        private Interpolator cInterpolator;
        // Common AnimationListener for animations
        private Animation.AnimationListener cListener;
        // Common fillBefore parameter for animations
        private boolean isFillBefore;

        /**
         * Constructor of non-static Animus instance
         *
         * @param copyStatic If True, then common parameters for animations
         *                   will be copied from static Animus,
         *                   else the default parameters will be set
         */
        public Instance(boolean copyStatic) {
            if (copyStatic) {
                cInterpolator = Animus.cInterpolator;
                cListener = Animus.cListener;
                isFillBefore = Animus.isFillBefore;
            }
            else {
                cInterpolator = new LinearInterpolator();
                cListener = null;
                isFillBefore = true;
            }
        }

        /**
         * Constructor of non-static Animus instance
         *
         * @param interpolator Common animation interpolator
         * @param listener Common AnimationListener
         */
        public Instance(Interpolator interpolator, Animation.AnimationListener listener) {
            cInterpolator = interpolator;
            cListener = listener;
            isFillBefore = true;
        }

        /**
         * Set default animations interpolator
         *
         * @param interpolatorType Type of interpolator
         */
        public Animus.Instance setCommonInterpolator(@IntRange(from=0,to=11) int interpolatorType) {
            cInterpolator = getNewInterpolator(interpolatorType);
            return this;
        }

        /**
         * Set default animations interpolator
         *
         * @param interpolator Common interpolator
         */
        public Animus.Instance setCommonInterpolator(Interpolator interpolator) {
            cInterpolator = interpolator;
            return this;
        }

        /**
         * Returns current common animation interpolator
         *
         * @return Current common interpolator
         */
        public Interpolator getCommonInterpolator() {
            return cInterpolator;
        }

        /**
         * Set default listener for all animations
         *
         * @param listener Common AnimationListener for animations
         */
        public Animus.Instance setCommonAnimationListener(Animation.AnimationListener listener) {
            cListener = listener;
            return this;
        }

        /**
         * Returns current common AnimationListener
         *
         * @return Current common AnimationListener
         */
        public Animation.AnimationListener getCommonAnimationListener() {
            return cListener;
        }

        /**
         * Set parameter fillBefore for all animations
         *
         * @param fillBefore  Common fillBefore parameter for animations
         */
        public Animus.Instance setCommonFillBefore(boolean fillBefore) {
            isFillBefore = fillBefore;
            return this;
        }

        /**
         * Returns current common FillBefore parameter
         *
         * @return Current common FillBefore value
         */
        public boolean isCommonFillBefore() {
            return isFillBefore;
        }

        private Animation setDefaults(Animation animation, int duration, boolean fillAfter) {
            animation.setDuration(duration);
            animation.setFillAfter(fillAfter);
            animation.setFillBefore(isFillBefore);
            animation.setFillEnabled(isFillBefore);
            animation.setInterpolator(cInterpolator);
            animation.setAnimationListener(cListener);
            return animation;
        }

        //================================== Animations ============================================

        /**
         * Animate moving with custom parameters relative to self
         *
         * @param fx staring horizontal factor
         * @param tx ending horizontal factor
         * @param fy starting vertical factor
         * @param ty ending vertical factor
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation move(float fx, float tx, float fy, float ty, int duration, boolean fillAfter) {
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, fx,
                    Animation.RELATIVE_TO_SELF, tx,
                    Animation.RELATIVE_TO_SELF, fy,
                    Animation.RELATIVE_TO_SELF, ty);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving with custom parameters relative to parent layout
         *
         * @param fx staring horizontal factor
         * @param tx ending horizontal factor
         * @param fy starting vertical factor
         * @param ty ending vertical factor
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation moveToParent(float fx, float tx, float fy, float ty, int duration, boolean fillAfter) {
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, fx,
                    Animation.RELATIVE_TO_PARENT, tx,
                    Animation.RELATIVE_TO_PARENT, fy,
                    Animation.RELATIVE_TO_PARENT, ty);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving with custom parameters
         * relative to self at start of animation
         * relative to parent at the end of animation
         *
         * @param fromSelfX staring horizontal factor relative to self
         * @param toParentX ending horizontal factor relative to parent
         * @param fromSelfY starting vertical factor relative to self
         * @param toParentY ending vertical factor relative to parent
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation moveFromSelfToParent(float fromSelfX, float toParentX, float fromSelfY, float toParentY, int duration, boolean fillAfter) {
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, fromSelfX,
                    Animation.RELATIVE_TO_PARENT, toParentX,
                    Animation.RELATIVE_TO_SELF, fromSelfY,
                    Animation.RELATIVE_TO_PARENT, toParentY);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving with custom parameters
         * relative to parent at start of animation
         * relative to self at the end of animation
         *
         * @param fromParentX staring horizontal factor relative to parent
         * @param toSelfX ending horizontal factor relative to self
         * @param fromParentY starting vertical factor relative to parent
         * @param toSelfY ending vertical factor relative to self
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation moveFromParentToSelf(float fromParentX, float toSelfX, float fromParentY, float toSelfY, int duration, boolean fillAfter) {
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, fromParentX,
                    Animation.RELATIVE_TO_SELF, toSelfX,
                    Animation.RELATIVE_TO_PARENT, fromParentY,
                    Animation.RELATIVE_TO_SELF, toSelfY);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving with custom parameters
         *
         * @param fx staring horizontal factor
         * @param fxRelation specifies how fx should be interpreted. One of
         *        Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, or Animation.RELATIVE_TO_PARENT.
         * @param tx ending horizontal factor
         * @param txRelation specifies how tx should be interpreted. One of
         *        Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, or Animation.RELATIVE_TO_PARENT.
         * @param fy starting vertical factor
         * @param fyRelation specifies how fy should be interpreted. One of
         *        Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, or Animation.RELATIVE_TO_PARENT.
         * @param ty ending vertical factor
         * @param tyRelation specifies how ty should be interpreted. One of
         *        Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, or Animation.RELATIVE_TO_PARENT.
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation moveCustom(float fx, int fxRelation, float tx, int txRelation,
                                float fy, int fyRelation, float ty, int tyRelation, int duration, boolean fillAfter) {

            Animation animation = new TranslateAnimation(fxRelation, fx, txRelation, tx, fyRelation, fy, tyRelation, ty);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving to the left of self
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation toLeft(int duration, boolean fillAfter){
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, -1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving to the right of self
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation toRight(int duration, boolean fillAfter){
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving to the top of self
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation toTop(int duration, boolean fillAfter){
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, -1.0f);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving to the bottom of self
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation toBottom(int duration, boolean fillAfter){
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 1.0f);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving beyond the left bound of parent layout
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation toLeftOfParent(int duration, boolean fillAfter){
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_PARENT, -1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving beyond the right bound of parent layout
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation toRightOfParent(int duration, boolean fillAfter){
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving beyond the top bound of parent layout
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation toTopOfParent(int duration, boolean fillAfter){
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_PARENT, -1.0f);

            return setDefaults(animation, duration, fillAfter);
        }


        /**
         * Animate moving beyond the bottom bound of parent layout
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation toBottomOfParent(int duration, boolean fillAfter){
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 1.0f);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving from the left of self to default position
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation fromLeft(int duration, boolean fillAfter){
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, -1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving from the right of self to default position
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation fromRight(int duration, boolean fillAfter){
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving from the top of self to default position
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation fromTop(int duration, boolean fillAfter){
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, -1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving from the bottom of self to default position
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation fromBottom(int duration, boolean fillAfter){
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving from left of parent to default position
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation fromLeftOfParent(int duration, boolean fillAfter){
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, -1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving from right of parent to default position
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation fromRightOfParent(int duration, boolean fillAfter){
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving from top of parent to default position
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation fromTopOfParent(int duration, boolean fillAfter){
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, -1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate moving from bottom of parent to default position
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return TranslateAnimation
         */
        public Animation fromBottomOfParent(int duration, boolean fillAfter){
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);

            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate visibility changing from visible to invisible
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return Alpha animation
         */
        public Animation hide(int duration, boolean fillAfter){
            Animation animation = new AlphaAnimation(1.0f, 0.0f);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate visibility changing from invisible to visible
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return Alpha animation
         */
        public Animation show(int duration, boolean fillAfter){
            Animation animation = new AlphaAnimation(0.0f, 1.0f);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate opacity changing with custom parameters
         *
         * @param startingOpacity opacity at the start of animation
         * @param endingOpacity opacity at the end of animation
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return AlphaAnimation
         */
        public Animation alpha(int startingOpacity, int endingOpacity, int duration, boolean fillAfter){
            Animation animation = new AlphaAnimation(startingOpacity, endingOpacity);
            return setDefaults(animation, duration, fillAfter);
        }


        /**
         * Animate scale with custom parameters relative to self
         *
         * @param fx horizontal factor at start of animation
         * @param tx horizontal factor at the end
         * @param fy vertical factor at start of animation
         * @param ty vertical factor at the and
         * @param pivotX pivot horizontal position
         * @param pivotY pivot vertical position
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return ScaleAnimation
         */
        public Animation scale(float fx, float tx, float fy, float ty, float pivotX, float pivotY, int duration, boolean fillAfter) {
            Animation animation = new ScaleAnimation(fx, tx, fy, ty, Animation.RELATIVE_TO_SELF, pivotX, Animation.RELATIVE_TO_SELF, pivotY);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate scale with custom parameters
         *
         * @param fx horizontal factor at start of animation
         * @param tx horizontal factor at the end
         * @param fy vertical factor at start of animation
         * @param ty vertical factor at the and
         * @param pivotX pivot horizontal position
         * @param pivotXRelation specifies how pivotX should be interpreted. One of
         *        Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF or Animation.RELATIVE_TO_PARENT
         * @param pivotY pivot vertical position
         * @param pivotYRelation specifies how pivotY should be interpreted. One of
         *        Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF or Animation.RELATIVE_TO_PARENT
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return ScaleAnimation
         */
        public Animation scaleCustom(float fx, float tx, float fy, float ty,
                                     float pivotX, int pivotXRelation, float pivotY, int pivotYRelation,
                                     int duration, boolean fillAfter) {
            Animation animation = new ScaleAnimation(fx, tx, fy, ty, pivotXRelation, pivotX, pivotYRelation, pivotY);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate scale from 0 to default size
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return ScaleAnimation
         */
        public Animation scaleFrom0to1(int duration, boolean fillAfter) {
            Animation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate scale from default size to 0
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return ScaleAnimation
         */
        public Animation scaleFrom1to0(int duration, boolean fillAfter) {
            Animation animation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
                    Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate scale from default size double size
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return ScaleAnimation
         */
        public Animation scaleFrom1to2(int duration, boolean fillAfter) {
            Animation animation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
                    Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate scale from double size to default size
         *
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return ScaleAnimation
         */
        public Animation scaleFrom2to1(int duration, boolean fillAfter) {
            Animation animation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
                    Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate rotation with custom parameters
         *
         * @param fd starting degrees
         * @param td ending degrees
         * @param pivotX horizontal pivot position relative to self
         * @param pivotXRelation specifies how pivotX should be interpreted. One of
         *        Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF or Animation.RELATIVE_TO_PARENT
         * @param pivotY vertical pivot position relative to self
         * @param pivotYRelation specifies how pivotY should be interpreted. One of
         *        Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF or Animation.RELATIVE_TO_PARENT
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return RotateAnimation
         */
        public Animation rotate(float fd, float td, float pivotX, int pivotXRelation,
                                float pivotY, int pivotYRelation, int duration, boolean fillAfter) {
            Animation animation =  new RotateAnimation(fd, td, pivotXRelation, pivotX, pivotYRelation, pivotY);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate rotation with custom parameters with pivot relative to self
         *
         * @param fd starting degrees
         * @param td ending degrees
         * @param pivotX horizontal pivot position relative to self
         * @param pivotY vertical pivot position relative to self
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return RotateAnimation
         */
        public Animation rotateSelf(float fd, float td, float pivotX, float pivotY, int duration, boolean fillAfter) {
            Animation animation = new RotateAnimation(fd, td, Animation.RELATIVE_TO_SELF, pivotX, Animation.RELATIVE_TO_SELF, pivotY);
            return setDefaults(animation, duration, fillAfter);
        }

        /**
         * Animate rotation with custom parameters with pivot relative to parent layout
         *
         * @param fd starting degrees
         * @param td ending degrees
         * @param pivotX horizontal pivot position relative to parent
         * @param pivotY vertical pivot position relative to parent
         * @param duration duration in milliseconds
         * @param fillAfter true if the animation should apply its transformation after it ends
         * @return RotateAnimation
         */
        public Animation rotateToParent(float fd, float td, float pivotX, float pivotY, int duration, boolean fillAfter) {
            Animation animation =  new RotateAnimation(fd, td, Animation.RELATIVE_TO_PARENT, pivotX, Animation.RELATIVE_TO_PARENT, pivotY);
            return setDefaults(animation, duration, fillAfter);
        }
    }
}
