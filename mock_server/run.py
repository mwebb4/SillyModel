import flask
app = flask.Flask(__name__)

def smoketest():
    return "hello"

def callback():
    return flask.jsonify({"col1":[1,2,3,4], "col2":[1,4,6,8]})

app.add_url_rule("/", view_func=smoketest)
app.add_url_rule("/users", view_func=callback)
app.run(debug=True)