package com.inlook.or.study.rx;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.inlook.or.study.TestApplication;
import com.inlook.or.study.utils.Logs;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * desc Rx
 *
 * @author: or
 * @since: on 2016/5/18.
 */
public class Rx {

    private static final String TAG = Rx.class.getSimpleName();

    public static void main(String[] args) {
        Rx rx = new Rx();
        //rx.rxCreate().subscribe(rx.getObserver());

        //rx.rxJust().subscribe(rx.getObserver());

        //rx.rxFrom().subscribe(rx.getObserver());

        rx.testAction1();
    }


    public Observable<String> rxCreate() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onCompleted();

            }
        });

        return observable;
    }

    public Observable<String> rxJust() {
        return Observable.just("Hello", "Hi");
    }

    public Observable<String> rxFrom() {
        String[] from = {"Hello", "Hi"};
        return Observable.from(from);
    }

    public Observer<String> getObserver() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Logs.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Logs.e(TAG, e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Logs.d(TAG, "onNext:" + s);
            }
        };


        return observer;
    }

    public void testAction1() {
        Observable.just("Hello", "Hi").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Logs.d(TAG, "testAction1:" + s);
            }
        });
    }

   /* public void showImage(final int drawableRes, final ImageView imageView) {
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                subscriber.onNext(TestApplication.getInstance().getResources().getDrawable(drawableRes));
                subscriber.onCompleted();
            }
         })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<Drawable>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(Drawable drawable) {
                    imageView.setImageDrawable(drawable);
                }
            });
    }*/

    public void testMap1(final String httpUrl) {
        Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(getImageUrl(httpUrl));
            }
        })

        .map(new Func1<String, Drawable>() {
            @Override
            public Drawable call(String s) {
                return getDrawableFromNet(s);
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Drawable>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Drawable drawable) {

            }
        });
    }

    private String getImageUrl(String httpUrl) {
        return "";
    }

    private Drawable getDrawableFromNet(String url) {
        return null;
    }


    /**
     * 打印每个学生修的课程（不使用for）
     * @param students
     */
    private void testFlatMap(Student[] students) {
        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                })
                .subscribe(new Observer<Course>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Course course) {
                        Logs.d(TAG,"flatMap:" + course.getName());
                    }
                });
    }

    private class Student{
        private String name;

        public Course[] getCourses() {
            return courses;
        }

        public void setCourses(Course[] courses) {
            this.courses = courses;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private Course[] courses;
    }

    private class Course {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;

    }
}
