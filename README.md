eclipseのワークスペース下にONEteamsフォルダを作成し、以下のファイルをコピー
.settings
src
.classpath
.project

document内のデータベース設計書を参考にDump20241227内のSQLを用いてDBを用意する

src/main/java/daoを各環境のDBに接続するように編集する

以下のファイルをネットからダウンロードする
jakarta.servlet.jsp.jstl-3.0.1.jar
jakarta.servlet.jsp.jstl-api-3.0.0.jar
mysql-connector-j-9.1.0.jar

src/main/webapp/WEB-INFにlibフォルダを作成し、ダウンロードしたjarファイルを配置する

src/main/webapp/login/login.jspから起動する