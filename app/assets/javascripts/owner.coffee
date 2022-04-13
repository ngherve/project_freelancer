$ ->
  ws = new WebSocket $("body").data("ws-url")
  ws.onmessage = (event) ->
    repo = JSON.parse event.data
    list = $('<ul>')

    for i in repo.user
        li = $('<li>')
        li.append(i.info)
        li.append('<br />')
        li.append('<br />')
        list.append(li)

    for i in repo.projects
        li = $('<li>')
        li.append('<a href="/owner/'+i.owner+'"  target="_blank" >'+i.id+'</a>').append('  ').append(i.time).append('  ').append(i.title).append('  type:  ').append(i.type).append('  skills:  ')
        for s in i.skills
          li.append('<a href="/skills/'+s.jid+'" target="_blank" >'+s.jname+'</a>').append('  ')
        li.append('<a href = "/getProjectIDStat/'+i.id+'" target="_blank">' + 'Stats'+'</a>')
        list.append(li)
    $("#divtest").prepend(list)




  ws.onopen = (event) ->
    event.preventDefault()
    # send the message to watch the stock
    ws.send(JSON.stringify({keyword:window.location.pathname.split("/").pop()}))
