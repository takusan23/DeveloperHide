# 開発者隠し DeveloperHide
USBデバッグを一時的にオフにしてアプリを起動して、終了したら戻す  
そのうちPlayストアに上がる

## 利用前に
以下のコマンドを叩く必要があります。  

```
adb shell pm grant io.github.takusan23.developerhide android.permission.WRITE_SECURE_SETTINGS
```

## 仕組み
USBデバッグ有効時に起動しないアプリを起動する前に、USBデバッグをOFFにする  
↓  
アプリを起動（startActivity）  
↓  
アプリ終了時にUSBデバッグを戻す。  

前後のUSBデバッグを無効、有効化するために、一回独自のActivityを噛ませてから`startActivity()`してます。  
`ShortcutHostActivity.kt`見てね
