##MarkdownPad2在win10安装后报错解决方法	
win10下安装MarkdownPad2后使用时报错:
>An error accurred with the HTML rendering component.

查询[官网][gw]信息：
>This issue has been specifically observed in Windows 8. You may see an error message as shown here, and no HTML will be rendered when you type in the Markdown Editor pane.
>
>To fix this issue, please try installing the Awesomium 1.6.6 SDK.
>
>If you continue to experience issues, please install Microsoft's DirectX End-User Runtimes (June 2010).

在win8及以上的系统(我的是win10)中需要下载[awesomium 1.6.6 SDK][awe]。安装后如果还有问题，继续安装[Microsoft's DirectX End-User Runtimes (June 2010)][mic]即可。

[gw]:http://markdownpad.com/faq.html#livepreview-directx
[awe]:http://markdownpad.com/download/awesomium_v1.6.6_sdk_win.exe
[mic]:http://www.microsoft.com/en-us/download/details.aspx?id=8109