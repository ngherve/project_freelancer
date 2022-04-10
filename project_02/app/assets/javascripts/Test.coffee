$ ->
  ws = new WebSocket $("body").data("ws-url")
  ws.onmessage = (event) ->
    repo = JSON.parse event.data
    $("#divtest").append(repo.msg)

  $("#addsymbolform").submit (event) ->
    event.preventDefault()
    $("#divtest").append("clicked")
    # send the message to watch the stock
    ws.send(JSON.stringify({keyword: $("#addsymboltext").val()}))
    # reset the form
    $("#addsymboltext").val("")