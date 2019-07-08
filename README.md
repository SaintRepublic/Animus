# Animus
A simple Java library to simplify the views animation.

Latest version: [ ![Download](https://api.bintray.com/packages/saintrepublic/Tools/animus/images/download.svg) ](https://bintray.com/saintrepublic/Tools/animus/_latestVersion)

<img src="https://github.com/SaintRepublic/Assets/blob/master/Samples/animus.gif" width="250">

## How to use
Implement it in your project via Gradle:
```java
dependencies {
    ...
    implementation 'com.saintrepublic.tools:animus:1.1'
}
```

Animus is a full static class, so anywhere you need you can write something like this:
```java
Animus.Alpha.show(duration, fillAfter);
```
And you will get a new Animation class instance.

## Example
```java
ImageView animatedView;

@Override
protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);

     animatedView = findViewById(R.id.animatedView);

     // You can set some common parameters for animations like this:
     Animus.setCommonInterpolator(Animus.Interpolation.BOUNCE);
}

public void onHideClick(View clickedButton) {
     
     // Animate hiding the view in 0.5 sec. and fill after
     animatedView.startAnimation(Animus.Alpha.hide(500, true));
}

public void onMoveClick(View clickedButton) {
     
     // Animate moving the view in 0.5 sec. and fill after
     animatedView.startAnimation(Animus.Move.To.bottom(500, true));
}

public void onScaleClick(View clickedButton) {
     
     // Animate scaling the view in 0.5 sec. and fill after
     animatedView.startAnimation(Animus.Scale.from1to0(500, true));
}

public void onRotateClick(View clickedButton) {
     
     // Animate rotating the view by 90 degrees in 0.5 sec. and fill after
     animatedView.startAnimation(Animus.Rotate.relativeToSelf(0, 90, 0.5f, 0.5f, 500, true));
}
```
