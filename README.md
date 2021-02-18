# Worms-Bot
Tugas Besar 1 IF2211 Strategi Algoritma

## Deskripsi Singkat
Program ini adalah sebuah bot untuk permainan "Worms" yang dibuat dengan strategi algoritma greedy. "Worms" adalah sebuah turn-based game dengan setiap pemain memiliki 3 worms dengan perannya masing-masing. Pemain dinyatakan menang jika ia berhasil bertahan hingga akhir permainan dengan cara mengeliminasi pasukan worms lawan menggunakan strategi tertentu.

## Strategi Greedy
Greedy yang digunakan adalah greedy by teammate location. Fokus utama adalah mengelompokkan worms dalam satu kluster di tengah map baru kemudian menyerang worms lawan jika ditemukan. Dalam prosesnya, akan diassign satu worms sebagai leader yang pergerakannya diikuti oleh dua worms lain. Strategi ini memiliki tradeoff di sisi keagresifan worms karena memprioritaskan berkumpul dahulu sebelum menyerang.

## Requirements
* Java Development Kit (JDK) 8 or higher (up to Java 11, may not work on latest Java version)
* Pastikan konfigurasi JAVA_HOME di Path sudah benar, untuk Windows 10 dapat membuka environment variables dan membuat system variables JAVA_HOME yang berisi directory tempat JDK berada
* IntelliJ Idea

## How to Build & Run
* ``` git clone https://github.com/rayfazt/greedy-worms.git ```
* Buka folder starter-bots dengan IntelliJ
* Lakukan modifikasi terhadap source code starter bot
* Setelah selesai, build dengan mengklik Maven di sudut kanan atas IntelliJ Idea, lalu klik java-sample-bot > Lifecycle > Install
* Akan dibuat folder "target" di directory yang sama dengan source, bot yang dihasilkan ada di dalam target dengan nama "java-sample-bot-jar-with-dependencies.jar"
* Untuk Windows, jalankan "run.bat". Untuk UNIX, jalankan "run.sh".
* By default, pertandingan akan dilakukan melawan reference bot yang terdapat dalam folder reference-bot. Konfigurasi dapat dilakukan dengan mengedit file "game-runner-config.json". Profil bot dapat diubah dengan memodifikasi "bot.json" di folder starter-bots.
* By default, program dijalankan di command line. Disarankan menggunakan [visualizer](https://github.com/dlweatherhead/entelect-challenge-2019-visualiser/releases/tag/v1.0f1) untuk melihat jalannya permainan dengan jelas.

## Author
Made with :heart: by:
* Rayhan Alghifari Fauzta (13519039)
* Raihan Astrada Fathurrahman (13519113)
* Irvin Andryan Pratomo (13519162)
