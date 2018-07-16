import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

#数据预处理
def read_dataset(fname):
    # 指定第一列作为行索引,标题
    data = pd.read_csv(fname, index_col=0)
    # 丢弃无用的数据
    data.drop(['Name', 'Ticket', 'Cabin'], axis=1, inplace=True)
    # 处理性别数据
    data['Sex'] = (data['Sex'] == 'male').astype('int')
    # 处理登船港口数据
    labels = data['Embarked'].unique().tolist()
    data['Embarked'] = data['Embarked'].apply(lambda n: labels.index(n))
    # 处理缺失数据
    data = data.fillna(0)
    return data

train = read_dataset(r'train.csv')
print(train.head())

#模型训练
from sklearn.model_selection import train_test_split

y = train['Survived'].values#获取survived的所有数据
X = train.drop(['Survived'], axis=1).values#axis=1表示列
#把数据分成训练数据集和交叉验证数据集
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2)

print('train dataset: {0},{1}; test dataset: {2},{3}'.format(
    X_train.shape,y_train.shape, X_test.shape, y_test.shape))

#建立决策树模型对数据拟合
from sklearn.tree import DecisionTreeClassifier

clf = DecisionTreeClassifier()
clf.fit(X_train, y_train)
train_score = clf.score(X_train, y_train)
test_score = clf.score(X_test, y_test)
print('train score: {0}; test score: {1}'.format(train_score, test_score))

#由于数据过拟合需要优化模型参数
from sklearn.tree import export_graphviz

with open("titanic.dot", 'w') as f:
    f = export_graphviz(clf, out_file=f)

# 1. 在电脑上安装 graphviz
# 2. 运行 `dot -Tpng titanic.dot -o titanic.png`
# 3. 在当前目录查看生成的决策树 titanic.png

# 参数选择 max_depth用于选择最大分层数
def cv_score(d):
    clf = DecisionTreeClassifier(max_depth=d)
    clf.fit(X_train, y_train)
    tr_score = clf.score(X_train, y_train)
    cv_score = clf.score(X_test, y_test)
    return (tr_score, cv_score)

depths = range(2, 15)#层数设置在2~14层
scores = [cv_score(d) for d in depths]
tr_scores = [s[0] for s in scores]#训练集分数列表
cv_scores = [s[1] for s in scores]#交叉验证分数列表

best_score_index = np.argmax(cv_scores)#交叉验证中分数最高的下标
best_score = cv_scores[int(best_score_index)]#得分最高的参数
best_param = depths[int(best_score_index)]#分数最高对应的层数
print('"最大层数的"+best param: {0}; best score: {1}'.format(best_param, best_score))

plt.figure(figsize=(10, 6), dpi=144)
plt.grid()
plt.xlabel('max depth of decision tree')
plt.ylabel('score')
plt.plot(depths, cv_scores, '.g-', label='cross-validation score')
plt.plot(depths, tr_scores, '.r--', label='training score')
plt.legend()
plt.show()

# 训练模型，并计算评分
def cv_score(val):
    clf = DecisionTreeClassifier(criterion='gini', min_impurity_decrease=val)
    clf.fit(X_train, y_train)
    tr_score = clf.score(X_train, y_train)
    cv_score = clf.score(X_test, y_test)
    return (tr_score, cv_score)

# 指定参数范围，分别训练模型，并计算评分
values = np.linspace(0, 0.5, 50)#在0~0.5上产生50个点
scores = [cv_score(v) for v in values]
tr_scores = [s[0] for s in scores]
cv_scores = [s[1] for s in scores]

# 找出评分最高的模型参数
best_score_index = np.argmax(cv_scores)
best_score = cv_scores[int(best_score_index)]
best_param = values[best_score_index]#找出最高分数对应的点
print('"前剪枝（基尼不纯数）的"+best param: {0}; best score: {1}'.format(best_param, best_score))

# 画出模型参数与模型评分的关系
plt.figure(figsize=(10, 6), dpi=144)
plt.grid()
plt.xlabel('threshold of entropy')
plt.ylabel('score')
plt.plot(values, cv_scores, '.g-', label='cross-validation score')
plt.plot(values, tr_scores, '.r--', label='training score')
plt.legend()
plt.show()



def plot_curve(train_sizes, cv_results, xlabel):
    train_scores_mean = cv_results['mean_train_score']
    train_scores_std = cv_results['std_train_score']
    test_scores_mean = cv_results['mean_test_score']
    test_scores_std = cv_results['std_test_score']

    plt.figure(figsize=(10, 6), dpi=144)
    plt.title('parameters turning')
    plt.grid()
    plt.xlabel(xlabel)
    plt.ylabel('score')
    plt.fill_between(train_sizes,
                     train_scores_mean - train_scores_std,
                     train_scores_mean + train_scores_std,
                     alpha=0.1, color="r")
    plt.fill_between(train_sizes,
                     test_scores_mean - test_scores_std,
                     test_scores_mean + test_scores_std,
                     alpha=0.1, color="g")
    plt.plot(train_sizes, train_scores_mean, '.--', color="r",
             label="Training score")
    plt.plot(train_sizes, test_scores_mean, '.-', color="g",
             label="Cross-validation score")

    plt.legend(loc="best")
    plt.show()

#网格搜索，寻找模型层数和阈值共同的最优值
from sklearn.model_selection import GridSearchCV

thresholds = np.linspace(0, 0.5, 50)
# Set the parameters by cross-validation
#数值参数矩阵
param_grid = {'min_impurity_decrease': thresholds}
#cv=5表示拿出其中一份作为交叉验证数据集，其余的作为训练数据集
clf = GridSearchCV(DecisionTreeClassifier(), param_grid, cv=5)
clf.fit(X, y)
print("best param: {0}\nbest score: {1}".format(clf.best_params_,
                                                clf.best_score_))
#clf.cv_results_所有计算结果
plot_curve(thresholds, clf.cv_results_, xlabel='gini thresholds')


#在多组参数之间选择最优的参数
entropy_thresholds = np.linspace(0, 1, 50)
gini_thresholds = np.linspace(0, 0.5, 50)

# Set the parameters by cross-validation
param_grid = [{'criterion': ['entropy'],#设定信息熵
               'min_impurity_decrease': entropy_thresholds},
              {'criterion': ['gini'],#设定使用基尼不纯度
               'min_impurity_decrease': gini_thresholds},#指定信息增益的阈值
              {'max_depth': range(2, 10)},#指定决策树的最大深度
              {'min_samples_split': range(2, 30, 2)}]#能创建样本数据集个数的大小

clf = GridSearchCV(DecisionTreeClassifier(), param_grid, cv=5)
clf.fit(X, y)
print("'组合参数之间的最优'+best param: {0};best score: {1}".format(clf.best_params_,
                                                clf.best_score_))

#生成决策树图形
clf = DecisionTreeClassifier(criterion='entropy', min_impurity_decrease=0.0)
clf.fit(X_train, y_train)
train_score = clf.score(X_train, y_train)
test_score = clf.score(X_test, y_test)
print('train score: {0}; test score: {1}'.format(train_score, test_score))

# 导出 titanic.dot 文件
with open("titanic.dot", 'w') as f:
    f = export_graphviz(clf, out_file=f)

# 1. 在电脑上安装 graphviz
# 2. 运行 `dot -Tpng titanic.dot -o titanic.png`
# 3. 在当前目录查看生成的决策树 titanic.png


