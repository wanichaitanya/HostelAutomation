import socket
import sys
import json
import RPi.GPIO as GPIO

host="192.168.43.132"
port=15000

server_socket=socket.socket(socket.AF_INET,socket.SOCK_STREAM)

server_socket.bind((host,port))

server_socket.listen(10)

GPIO.setmode(GPIO.BOARD)
GPIO.setup(7,GPIO.OUT)
GPIO.setup(11,GPIO.OUT)

while True:
    client,addr=server_socket.accept()
    recv_msg = client.recv(1024)
    recv_data=recv_msg.decode('utf-8')

    if recv_data=="exit":
        client.close
        GPIO.cleanup()
        break

    elif recv_data=="on1":
        GPIO.output(7,False)
        print("relay 7 on")

    elif recv_data=="off1":
        GPIO.output(7,True)
        print("relay 7 off")

    elif recv_data=="on2":
        GPIO.output(11,False)
        print("relay 5 on")

    elif recv_data=="off2":
        GPIO.output(11,True)
        print("relay 5 off")

    elif recv_data=="":
        break
    print("client send:- "+recv_data)

client.close
GPIO.cleanup()
