$ ->
  ws = new WebSocket $("body").data("ws-url")
  ws.onmessage = (event) ->
    repo = JSON.parse event.data
    list = $('<ul>')

    for i in repo.stats
        li = $('<li>')
        li.append(i.stat)
        li.append('<br />')
        li.append('<br />')
        list.append(li)

    $("#divtest").prepend(list)

  ws.onopen = (event) ->
    event.preventDefault()
    # send the message to watch the stock
    ws.send(JSON.stringify({keyword:window.location.pathname.split("/").pop()}))
