import pandas as pd
from sklearn import svm, preprocessing

df = pd.read_csv('/Users/ahmad/Desktop/CleanStockData.csv')
#print(df)

x = df.drop(["BuyOrNot", "nextYearProfit"], axis=1).values
#print(x)
x = preprocessing.scale(x)
x = preprocessing.normalize(x)
y = df['BuyOrNot'].values
test_size = 300;

x_train = x[:test_size]
y_train = y[:test_size]
x_test = x[test_size:]
y_test = y[test_size:]

clf = svm.LinearSVC()
clf.fit(x_train, y_train)
print(clf.score(x_test, y_test))
#for x,y in zip(x_test, y_test):
    #print(f"Model: {clf.predict([x])[0]}, Actual: {y}")


