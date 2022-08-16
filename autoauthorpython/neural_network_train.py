import numpy as np
import matplotlib.pyplot as plt

from keras.models import Sequential
from keras.layers import Dense, Dropout, BatchNormalization
from keras.optimizers import Adam
from keras.preprocessing.text import Tokenizer

from os import walk
import re
import tensorflow as tf
import neural_network_functions as nnf


def train_neural_network(group_id):
    # Загрузка текстов для обучения
    text_for_train = nnf.get_all_text_from_dir(
        'C:\Program Files\Apache Software Foundation\Tomcat 9.0\\tmpfiles\\' + group_id + '\\train')
    text_for_test = nnf.get_all_text_from_dir(
        'C:\Program Files\Apache Software Foundation\Tomcat 9.0\\tmpfiles\\' + group_id + '\\test')

    class_name = []
    f = []
    for (dirpath, dirnames, filenames) in walk(
            'C:\Program Files\Apache Software Foundation\Tomcat 9.0\\tmpfiles\\' + group_id + '\\train'):
        f.extend(filenames)
        break

    for i in range(len(f)):
        author_name = re.findall(r'\(.*?\)', f[i])[0]
        class_name.append(author_name[1:len(author_name) - 1])

    count_classes = len(class_name)

    # Подготовка данных
    max_count_word = 20000
    tokenizer = Tokenizer(num_words=max_count_word, filters='<=>?@[\\]^_`{|}~\t\n\r«»!–"—#$%&()*+,-./:;', lower=True,
                          split=' ', char_level=False)
    tokenizer.fit_on_texts(text_for_train)

    train_sequence = tokenizer.texts_to_sequences(text_for_train)
    test_sequence = tokenizer.texts_to_sequences(text_for_test)

    step = 100
    len_segment = 8000

    # Формирование обучающей и тестовой выборок
    x_train, y_train = nnf.get_train_and_verification_matrix(train_sequence, step, len_segment)
    x_test, y_test = nnf.get_train_and_verification_matrix(test_sequence, step, len_segment)

    x_train_matrix = tokenizer.sequences_to_matrix(x_train.tolist())
    x_test_matrix = tokenizer.sequences_to_matrix(x_test.tolist())

    # Структура нейронной сети
    model = Sequential()
    model.add(BatchNormalization())
    model.add(Dense(46, input_dim=max_count_word, activation="relu"))
    model.add(Dropout(0.3))
    model.add(BatchNormalization())
    model.add(Dense(10, input_dim=max_count_word, activation="relu"))
    model.add(Dropout(0.3))
    model.add(BatchNormalization())
    model.add(Dense(len(text_for_train), activation='sigmoid'))

    model.compile(optimizer='adam',
                  loss=tf.keras.losses.categorical_crossentropy,
                  metrics=['accuracy'])

    # Обучение нейронной сети
    history = model.fit(x_train_matrix, y_train, epochs=5, batch_size=256, validation_data=(x_test_matrix, y_test))
    plt.plot(history.history['accuracy'], label='Точность на обучающем наборе')
    plt.plot(history.history['val_accuracy'], label='Точность на проверочном наборе')
    plt.xlabel('Эпоха')
    plt.ylabel('Точность')
    plt.legend()
    plt.show()

    text_ready_for_prediction = nnf.prepare_text_for_identification(tokenizer, text_for_test[3], len_segment, step)
    curr_pred = model.predict(text_ready_for_prediction)
    curr_out = np.argmax(curr_pred, axis=1)

    probability = [0] * count_classes

    for i in range(len(text_for_test)):
        probability[i] = np.count_nonzero(curr_out == i) / len(curr_out)

    recognized_class = np.argmax(probability)

    print("Текст написан:", class_name[recognized_class], "с вероятностью", probability[recognized_class])

    model.save('C:\Program Files\Apache Software Foundation\Tomcat 9.0\\tmpfiles\\' + group_id + '\\model\\model.h5')

train_neural_network('5')