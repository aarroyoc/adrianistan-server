#!/bin/bash
DIALOG=whiptail
TITLE="Adrianistán Server Minecraft"
TMPFILE=/tmp/adrianistan

function configureServer {
	DIR=$1
	$DIALOG --title "Adrianistán Server Minecraft" --msgbox "Ahora se va a configurar el servidor nuevo. Contesta a las preguntas" 20 40
	#HARDCORE
	$DIALOG --title "Adrianistán Server Minecraft" --yesno "Activar Hardcore" 20 40
	if [ $? == 0 ]; then
		HARDCORE=true
	else
		HARDCORE=false
	fi
	#NOPREMIUM
	$DIALOG --title "Adrianistán Server Minecraft" --yesno "Activar para NoPremium" 20 40
	if [ $? == 0 ]; then
		ONLINE_MODE=false
	else
		ONLINE_MODE=true
	fi
	#PVP
	$DIALOG --title "Adrianistán Server Minecraft" --yesno "Activar PVP" 20 40
	if [ $? == 0 ]; then
		PVP=true
	else
		PVP=false
	fi
	#COMMAND BLOCK
	$DIALOG --title "Adrianistán Server Minecraft" --yesno "Activar Bloques de Comandos" 20 40
	if [ $? == 0 ]; then
		COMMAND_BLOCK=true
	else
		COMMAND_BLOCK=false
	fi
	#SERVER NAME
	$DIALOG --title "Adrianistán Server Minecraft" --inputbox "Nombre del Server" 20 40 2>$TMPFILE
	SERVER_NAME=$(cat $TMPFILE)
	#MAXPLAYERS
	$DIALOG --title "Adrianistán Server Minecraft" --inputbox "Número máximo de jugadores" 20 40 2>$TMPFILE
	MAX_PLAYERS=$(cat $TMPFILE)
	m4 -DHARDCORE=$HARDCORE -DONLINE_MODE=$ONLINE_MODE -DPVP=$PVP -DCOMMAND_BLOCK=$COMMAND_BLOCK -DSERVER_NAME=$SERVER_NAME -DMAX_PLAYERS=$MAX_PLAYERS server.properties.m4 > $DIR/server.properties
}
function configureNoIP {
	sudo noip2
}
function startServer {
	$DIALOG --title "Adrianistán Server Minecraft" --yesno "¿Quieres configurar No-IP?" 20 40
	case $? in
		0) configureNoIP ;;
		1) echo "SKIP CONFIGURE NO-IP";;
		255) echo "EXIT";;
	esac
	java -jar minecraft_server.jar
}
function downloadServer {
	mkdir $1
	cd $1
	wget -O minecraft_server.jar https://s3.amazonaws.com/Minecraft.Download/versions/$1/minecraft_server.$1.jar
	cd ..
	configureServer $1
}
function downloadBukkitServer {
	mkdir $1
	cd $1
	wget -O minecraft_server.jar http://dl.bukkit.org/downloads/craftbukkit/get/02389_1.6.4-R2.0/craftbukkit.jar
	cd ..
}
function setServerProperties {
	echo "CONF"
}
function checkBukkitServer {
	VERSION=$1
	if [ ! -d "1.6.4-BUKKIT" ]; then
		$DIALOG --title "Adrianistán Server Minecraft" --yesno "La carpeta del server no existe. ¿Quieres crearla?" 20 40
		case $? in
			0) downloadBukkitServer "1.6.4-BUKKIT";;
			1) echo "EXIT";;
			255) echo "EXIT";;
		esac
	fi
	cd "1.6.4-BUKKIT"
	startServer
}
function checkServer {
	echo "Checking server for $1"
	VERSION=$1
	if [ ! -d "$VERSION" ]; then
		$DIALOG --title "Adrianistán Server Minecraft" --yesno "La carpeta del server no existe. ¿Quieres crearla?" 20 40
		case $? in
			0) downloadServer "$VERSION";;
			1) echo "EXIT";;
			255) echo "EXIT";;
		esac
	fi
	cd $VERSION
	startServer
}
$DIALOG --title "Adrianistán Server Minecraft" --msgbox "Bienvenido al configurador de Adrianistán Minecraft" 20 40
$DIALOG --title "Adrianistán Server Minecraft" --menu "Selecciona la versión del server" 20 40 10 \
1 "Minecraft 1.7.4" \
2 "Minecraft 1.6.4" \
3 "Minecraft 1.5.2" \
4 "CraftBukkit 1.6.4" 2>$TMPFILE

response=$(cat $TMPFILE)
echo "VERSION: $response"
case $response in
 1) 
	checkServer "1.7.4"
	;;
 2) 
	checkServer "1.6.4"
	;;
 3) 
	checkServer "1.5.2"
	;;
 4)
	checkBukkitServer "1.6.4"
	;;
 255) echo "EXIT" ;;
esac
