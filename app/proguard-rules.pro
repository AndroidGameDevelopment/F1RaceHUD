# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

############################################################
## KEEP ALL PACKET + FORMATTER CLASSES (game23/24/25)
############################################################
-keep class com.codaers.f1racehud.f1.game23.** { *; }
-keep class com.codaers.f1racehud.f1.game24.** { *; }
-keep class com.codaers.f1racehud.f1.game25.** { *; }

############################################################
## KEEP PacketHeader + getHeader() (reflection)
############################################################
-keep class com.codaers.f1racehud.PacketHeader { *; }

# Keep getHeader() even if accessed only via reflection
-keepclassmembers class * {
    com.codaers.f1racehud.PacketHeader getHeader(...);
}

############################################################
## KEEP VIEWMODEL CONSTRUCTORS (AndroidViewModelFactory)
############################################################
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}

############################################################
## KEEP KOTLIN METADATA (reflection stability)
############################################################
-keep class kotlin.Metadata { *; }