import matplotlib.pyplot as plt
import numpy as np

from time import time
from sklearn.datasets import load_files

print("loading train dataset ...")
t = time()
#load_files会把这个目录下的所有文档都读入内存，会根据子目录名称打上标签
news_train = load_files('F:\\学习视频3\\图书资料\\机器学习\\code\\datasets\\mlcomp\\379\\train')
#news_train.data包括了文档的所有文本信息
#news_train.target包含了文档所属类别
#news_train.target_names是类别名称
print("summary: {0} documents in {1} categories.".format(
    len(news_train.data), len(news_train.target_names)))
print("done in {0} seconds".format(time() - t))

#计算TF-IDF表达的权重信息构成的向量
from sklearn.feature_extraction.text import TfidfVectorizer

print("vectorizing train dataset ...")
t = time()
#TfidfVectorizer类是用来把所有的文档转化为矩阵，每行代表一个文档，每行中的元素代表
#词语的重要性，有TF-IDF表示
vectorizer = TfidfVectorizer(encoding='latin-1')
#fit()是完成词语库分析操作，transform（）会把每篇文档转换成向量
X_train = vectorizer.fit_transform((d for d in news_train.data))
print("n_samples: %d, n_features: %d" % X_train.shape)
print("number of non-zero features in sample [{0}]: {1}".format(
    news_train.filenames[0], X_train[0].getnnz()))
print("done in {0} seconds".format(time() - t))

#模型训练
from sklearn.naive_bayes import MultinomialNB

print("traning models ...".format(time() - t))
t = time()
y_train = news_train.target
clf = MultinomialNB(alpha=0.0001)
clf.fit(X_train, y_train)
train_score = clf.score(X_train, y_train)
print("train score: {0}".format(train_score))
print("done in {0} seconds".format(time() - t))

#测试模型
print("loading test dataset ...")
t = time()
news_test = load_files('F:\\学习视频3\\图书资料\\机器学习\\code\\datasets\\mlcomp\\379\\test')
print("summary: {0} documents in {1} categories.".format(
    len(news_test.data), len(news_test.target_names)))
print("done in {0} seconds".format(time() - t))

#向量化
print("vectorizing test dataset ...")
t = time()
X_test = vectorizer.transform((d for d in news_test.data))
y_test = news_test.target
print("n_samples: %d, n_features: %d" % X_test.shape)
print("number of non-zero features in sample [{0}]: {1}".format(
    news_test.filenames[0], X_test[0].getnnz()))
print("done in %fs" % (time() - t))

pred = clf.predict(X_test[0])
print("predict: {0} is in category {1}".format(
    news_test.filenames[0], news_test.target_names[pred[0]]))
print("actually: {0} is in category {1}".format(
    news_test.filenames[0], news_test.target_names[news_test.target[0]]))

#模型评价
print("predicting test dataset ...")
t = time()
pred = clf.predict(X_test)
print("done in %fs" % (time() - t))
#每个类别的预测准确性
from sklearn.metrics import classification_report

print("classification report on test set for classifier:")
print(clf)
print(classification_report(y_test, pred,
                            target_names=news_test.target_names))

from sklearn.metrics import confusion_matrix

cm = confusion_matrix(y_test, pred)
print("confusion matrix:")
print(cm)

#对混淆矩阵进行可视化处理
# Show confusion matrix
plt.figure(figsize=(8, 8), dpi=144)
plt.title('Confusion matrix of the classifier')
ax = plt.gca()
ax.spines['right'].set_color('none')
ax.spines['top'].set_color('none')
ax.spines['bottom'].set_color('none')
ax.spines['left'].set_color('none')
ax.xaxis.set_ticks_position('none')
ax.yaxis.set_ticks_position('none')
ax.set_xticklabels([])
ax.set_yticklabels([])
plt.matshow(cm, fignum=1, cmap='gray')
plt.colorbar()
plt.show()

