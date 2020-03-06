$(function () {
    var editor = editormd("publish-editor", {
        width: "100%",
        height: 320,
        path: "/js/lib/",
        delay: 0,
        syncScrolling: true,
        watch: false,
        placeholder: "请详细描述您的问题......",
        imageUpload: true,
        imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL: "/file/upload"
    });
});