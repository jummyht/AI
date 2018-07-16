import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

# 加载数据
data = pd.read_csv('diabetes.csv')
print('dataset shape {}'.format(data.shape))
print(data.head())
#groupby根据什么字段分组
print(data.groupby("Outcome").size())

X = data.iloc[:, 0:8]
Y = data.iloc[:, 8]
print('shape of X {}; shape of Y {}'.format(X.shape, Y.shape))
#把数据划分为训练数据和测试数据
from sklearn.model_selection import train_test_split
X_train, X_test, Y_train, Y_test = train_test_split(X, Y, test_size=0.2)
#这里使用了3种模型：普通knn，带权重的knn，指定半径的knn
from sklearn.neighbors import KNeighborsClassifier, RadiusNeighborsClassifier
#构造三个模型
models = []
models.append(("KNN", KNeighborsClassifier(n_neighbors=2)))
models.append(("KNN with weights", KNeighborsClassifier(
    n_neighbors=2, weights="distance")))
models.append(("Radius Neighbors", RadiusNeighborsClassifier(
    n_neighbors=2, radius=500.0)))

results = []
for name, model in models:
    model.fit(X_train, Y_train)
    results.append((name, model.score(X_test, Y_test)))
for i in range(len(results)):
    print("name: {}; score: {}".format(results[i][0],results[i][1]))
#普通的knn效果最好

##多次随机分配数据集合交叉验证数据集，求模型准确性评分的平均值
from sklearn.model_selection import KFold
from sklearn.model_selection import cross_val_score

results = []
for name, model in models:
    kfold = KFold(n_splits=10)#把训练集分成10分，1分作为交叉验证数据集来计算模型准确性
    cv_result = cross_val_score(model, X, Y, cv=kfold)
    results.append((name, cv_result))
for i in range(len(results)):
    print("name: {}; cross val score: {}".format(
        results[i][0],results[i][1].mean()))
#发现结果还是普通的knn最好，现在使用普通的knn来训练
knn = KNeighborsClassifier(n_neighbors=2)
knn.fit(X_train, Y_train)
train_score = knn.score(X_train, Y_train)
test_score = knn.score(X_test, Y_test)
print("普通的knn:train score: {}; test score: {}".format(train_score, test_score))

from sklearn.model_selection import ShuffleSplit
from sklearn.model_selection import learning_curve #调用库的画图方法
#画出学习曲线
# knn = KNeighborsClassifier(n_neighbors=2)
# cv = ShuffleSplit(n_splits=10, test_size=0.2, random_state=0)
# plt.figure(figsize=(10, 6), dpi=200)
# learning_curve(plt, knn, "Learn Curve for KNN Diabetes",X, Y, cv=cv)

#选出关系最大的2个特征值，比较三种knn那种最好
from sklearn.feature_selection import SelectKBest

selector = SelectKBest(k=2)
X_new = selector.fit_transform(X, Y)
print(X_new[0:5])

results = []
for name, model in models:
    kfold = KFold(n_splits=10)
    cv_result = cross_val_score(model, X_new, Y, cv=kfold)
    results.append((name, cv_result))
for i in range(len(results)):
    print("name: {}; cross val score: {}".format(
        results[i][0],results[i][1].mean()))

# 画出数据
plt.figure(figsize=(10, 6), dpi=200)
plt.ylabel("BMI")
plt.xlabel("Glucose")
plt.scatter(X_new[Y==0][:, 0], X_new[Y==0][:, 1], c='r', s=20, marker='o')# 画出样本
plt.scatter(X_new[Y==1][:, 0], X_new[Y==1][:, 1], c='g', s=20, marker='^')# 画出样本
plt.show()

