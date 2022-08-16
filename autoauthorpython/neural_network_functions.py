import numpy as np
from keras import utils
from keras.preprocessing.text import Tokenizer
from os import walk



def get_all_text_from_dir(dirpath):
    train_files = []
    for (dirpath, dirnames, filenames) in walk(dirpath):
        train_files.extend(filenames)
        break

    text_for_train = []
    for i in range(len(train_files)):
        text_for_train.append(read_file(dirpath + '\\' + train_files[i]))

    return text_for_train


def read_file(file_name):
    f = open(file_name, 'r', encoding="utf-8")
    text = f.read()

    return text


def get_list_segment(word_indexes, len_segment, step):
    x = []
    i = 0
    len_words = len(word_indexes)
    while (i + len_segment <= len_words):
        x.append(word_indexes[i:i + len_segment])
        i += step

    return x


def get_train_and_verification_matrix(sequences, step, len_segment):
    x, y = [], []
    num_classes = len(sequences)
    for i, sequence in enumerate(sequences):
        current = get_list_segment(sequence, len_segment, step)
        x += current
        y += [utils.to_categorical(i, num_classes).tolist()] * len(current)

    return np.array(x), np.array(y)


def prepare_text_for_identification(tokenizer, text, len_segment, step):
    test_word_indexes = tokenizer.texts_to_sequences([text])
    sample = get_list_segment(test_word_indexes[0], len_segment, step)
    x_text = tokenizer.sequences_to_matrix(sample)

    return np.array(x_text)
