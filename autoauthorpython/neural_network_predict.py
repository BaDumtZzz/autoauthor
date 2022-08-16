from tensorflow import keras
import numpy as np
from keras.preprocessing.text import Tokenizer
from os import walk
import re
import neural_network_functions as nnf


def identify_author(message_from_java):
    group_id = re.findall(r'\(.*?\)', message_from_java)[0]
    group_id = group_id[1:len(group_id) - 1]
    train_files = []
    for (dirpath, dirnames, filenames) in walk(
            'C:\Program Files\Apache Software Foundation\Tomcat 9.0\\tmpfiles\\' + group_id + '\\train'):
        train_files.extend(filenames)
        break

    text_for_train = []
    for i in range(len(train_files)):
        text_for_train.append(nnf.read_file(
            'C:\Program Files\Apache Software Foundation\Tomcat 9.0\\tmpfiles\\' + group_id + '\\train\\' + train_files[
                i]))

    max_count_word = 20000
    tokenizer = Tokenizer(num_words=max_count_word, filters='!–"—#$%&()*+,-./:;<=>?@[\\]^_`{|}~\t\n\r«»', lower=True,
                          split=' ', char_level=False)
    tokenizer.fit_on_texts(text_for_train)

    len_segment = 8000
    step = 100

    text_for_test = []
    file_name = re.findall(r'\[.*?\]', message_from_java)[0]
    file_name = file_name[1:len(file_name) - 1]
    text_for_test.append(nnf.read_file(
        'C:\Program Files\Apache Software Foundation\Tomcat 9.0\\tmpfiles\\' + group_id + '\\' + file_name))

    text_ready_for_prediction = nnf.prepare_text_for_identification(tokenizer, text_for_test[0], len_segment, step)
    model_loaded = keras.models.load_model(
        'C:\Program Files\Apache Software Foundation\Tomcat 9.0\\tmpfiles\\' + group_id + '\\model\\model.h5')
    curr_pred = model_loaded.predict(text_ready_for_prediction)
    curr_out = np.argmax(curr_pred, axis=1)

    class_name = []
    for i in range(len(train_files)):
        authorName = re.findall(r'\(.*?\)', train_files[i])[0]
        class_name.append(authorName[1:len(authorName) - 1])
    n_classes = len(class_name)
    probability = [0] * n_classes

    for i in range(len(class_name)):
        probability[i] = np.count_nonzero(curr_out == i) / len(curr_out)

    recognized_class = np.argmax(probability)

    print("Наиболее вероятно текст написан:", class_name[recognized_class], "с вероятностью",
          probability[recognized_class])

    for i in range(len(class_name)):  # Проходим по всем классам
        print("Текст написан:", class_name[i], "с вероятностью", probability[i])

    result = ""
    for i in range(len(class_name)):  # Проходим по всем классам
        if (probability[i] != 0):
            result += "(" + class_name[i] + ")" + "[" + str(probability[i] * 100) + "]"

    return result

identify_author('(5)'+'[Morf.txt])')
