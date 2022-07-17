import flask
import pandas as pd
from sklearn import datasets


n_samples = 100
n_features = 5

X, y = datasets.make_regression(
    n_samples=n_samples,
    n_features=n_features,
    n_informative=3,
    noise=10,
    coef=False,
    random_state=0,
)

df = pd.DataFrame(
    data=X,
    columns= ['x_{}'.format(i) for i in range(n_features)]
)

df['y'] = y

# Save to file:
#with open('./data.csv', 'w') as f:
#    df.to_csv(f, index=False)

data = df.to_dict(orient='list')

app = flask.Flask(__name__)

def smoketest():
    return "hello"

def callback():
    return flask.jsonify(data)

app.add_url_rule("/", view_func=smoketest)
app.add_url_rule("/data", view_func=callback)
app.run(debug=True)