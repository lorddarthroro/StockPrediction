import pandas as pd
from sklearn import svm, preprocessing, linear_model
from sklearn.linear_model import Perceptron

df = pd.read_csv('/Users/ahmad/Desktop/CleanStockData.csv')
#print(df)

x = df.drop(["BuyOrNot", "nextYearProfit"], axis=1).values
#print(x)
x = preprocessing.scale(x)
x = preprocessing.normalize(x)
y = df['BuyOrNot'].values
#y = df['nextYearProfit'].values
test_size = 300;

x_train = x[:test_size]
y_train = y[:test_size]
x_test = x[test_size:]
y_test = y[test_size:]

clf = svm.LinearSVC()
#clf = linear_model.Ridge(alpha=.5)
clf = Perceptron(tol=1e-3, random_state=0) #use with 'BuyOrNot'
#clf = svm.
clf.fit(x_train, y_train)
print(clf.score(x_test, y_test))
for x,y in zip(x_test, y_test):
    print(f"Model: {clf.predict([x])[0]}, Actual: {y}")


