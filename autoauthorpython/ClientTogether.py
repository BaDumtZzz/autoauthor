import threading
import socket

import re
import neural_network_train as nnt
import neural_network_predict as nnp
import connect_oracle as connorcl


class Client():
    def __init__(self, messageFromJava):
        command = re.findall(r'\<.*?\>', messageFromJava)[0]
        command = command[1:len(command)-1]
        if command == "identify":
            address = ('127.0.0.1', 8086)
            s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            s.connect(address)
            sendFile = SendFile(s, messageFromJava)
            sendFile.start()
        elif command == "train":
            groupId = re.findall(r'\(.*?\)', messageFromJava)[0]
            groupId = groupId[1:len(groupId)-1]
            nnt.train_neural_network(groupId)
            connorcl.update_model(groupId)


class SendFile(threading.Thread):
    def __init__(self, sock, unknownFilePath):
        threading.Thread.__init__(self)
        self.sock = sock
        self.unknownFilePath = unknownFilePath

    def run(self):
        prediction = nnp.identify_author(self.unknownFilePath)
        name = str(prediction) + '\r'
        self.sock.send(str.encode(name, "windows-1251"))
        print('send file finished.')
        self.sock.close()
        print('close socket')

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind(('127.0.0.1', 2000))
server.listen(4)
while True:
    print('Working...')
    client_socket, address = server.accept()
    data = client_socket.recv(1024)
    print(data.decode('utf-8'))
    c = Client(data.decode('utf-8'))
print('shutdown')
