__author__ = 'manarPC'

from bottle import run, post, request, error, get
import json
import urllib2


@error(404)
def error404(error):
    return 'Not Found'


#send message from server to android device using GCM 
@get('/GCM')
def GCM():
    file = open('newfile.txt','r')
    regId = file.read()
    json_data = {"registration_ids": [regId],}
    url ="https://android.googleapis.com/gcm/send"
    apiKey ="AIzaSyCs3E0Fb8pIbSuLsBLL6brr3qU6V-CR9bw"
    myKey ="key=" + apiKey
    message = json.dumps(json_data)
    headers ={'Content-Type': 'application/json', 'Authorization': myKey}
    req = urllib2.Request(url,message,headers)
    f = urllib2.urlopen(req)
    response = json.loads(f.read())
    return response


#register android device id to the server 
@post('/gcmRegKey')
def gcmRegKey():
    data = request.body.read()
    entity = json.loads(data)
    regId= entity['regid']
    file = open("newfile.txt", "w")
    file.write(regId)
    file.close()


run(host='192.168.1.102', port=7777, debug=True, reloader=True)


