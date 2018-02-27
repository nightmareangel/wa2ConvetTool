/*#^#
支付宝转微信配置信息
说明：
此文件并非传统json
通过JSON.parse 进行转换需要做特殊处理

可以直接在字符串中使用\ 无需转义
可以使用 模板字符串 `` 模板中支持换行
这样所见即所得 很方便

此文件的注释方式为：传统注释符+ #^#

#^#*/
{
    //#^#js文件中需要转换方法入参对象的属性名称和类型
    "cpas": [
        {
            //#^#方法名
            "pname":"showToast",
            //#^#转换规则
            "rps": [
                {
                    //#^#转换类型 ChangeKEY：修改属性 ，AddKEY 添加属性
                    "type": "ChangeKEY",
                    //#^#转换类型 ChangeKEY：需要修改的属性名
                    "kfrom": "content",
                    //#^#转换类型 ChangeKEY：修改后的属性名
                    "kto": "title"
                },
                {
                    "type": "ChangeKEY",
                    "kfrom": "type",
                    "kto": "icon"
                }
            ]
        },
        {
            "pname":"alert",
            "rps": [
                {
                    "type": "AddKEY",
                    //#^#添加的属性名
                    "kto": "showCancel",
                    //#^#添加的属性值
                    "vto": false
                }
            ]
        },
        {
            "pname":"confirm",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "confirmButtonText",
                    "kto": "confirmText"
                },
                {
                    "type": "ChangeKEY",
                    "kfrom": "cancelButtonText",
                    "kto": "cancelText"
                }
            ]
        },
        {
            "pname":"showActionSheet",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "items",
                    "kto": "itemList"
                }
            ]
        },
        {
            "pname":"setTextAlign",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "textAlign",
                    "kto": "align"
                }
            ]
        },
        {
            "pname":"previewImage",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "current",
                    "kto": "current",
                    "vtype": "string"
                }
            ]
        },
        {
            "pname":"getLocation",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "type",
                    "kto": "type",
                    "vtype": "string"
                }
            ]
        },
        {
            "pname":"saveImage",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "url",
                    "kto": "filePath"
                }
            ]
        },
        {
            "pname":"httpRequest",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "headers",
                    "kto": "header"
                }
            ]
        },
        {
            "pname":"uploadFile",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "fileName",
                    "kto": "name"
                }
            ]
        },
        {
            "pname":"connectSocket",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "headers",
                    "kto": "header"
                }
            ]
        },
        {
            "pname":"setClipboard",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "text",
                    "kto": "data"
                }
            ]
        },
        {
            "pname":"makePhoneCall",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "number",
                    "kto": "phoneNumber"
                }
            ]
        }

    ],
    //#^#转换的扩展名
    "csuffixs": [
        {
            "from": "axml",
            "to": "wxml"
        },
        {
            "from": "acss",
            "to": "wxss"
        }
    ],
    //#^#js文件中需要转换的方法
    "cmethods": [
        {
            "from": "httpRequest",
            "to": "request"
        },
        {
            "from": "getAuthCode",
            "to": "login"
        },
        {
            "from": "confirm",
            "to": "showModal"
        },
        {
            "from": "alert",
            "to": "showModal"
        },
        {
            "from": "getAuthUserInfo",
            "to": "getUserInfo"
        },
        {
            "from": "tradePay",
            "to": "requestPayment"
        },
        {
            "from": "saveImage",
            "to": "saveImageToPhotosAlbum"
        },
        {
            "from": "setNavigationBar",
            "to": "setNavigationBarTitle"
        },
        {
            "from": "setNavigationBar",
            "to": "setNavigationBarColor"
        },
        {
            "from": "getClipboard",
            "to": "getClipboardData"
        },
        {
            "from": "setClipboard",
            "to": "setClipboardData"
        },
        {
            "from": "toTempFilePath",
            "to": "canvasToTempFilePath"
        },
        {
            "from": "scan",
            "to": "scanCode"
        },
        {
            "from": "disconnectBLEDevice",
            "to": "closeBLEConnection"
        },
        {
            "from": "getStorageSync\(\s*{\s*key:(\S+)\s*}\s*\).data",
            "to": "getStorageSync($1)"
        },
        {
            "from": "setStorageSync\(\s*{\s*key:(\S+)\s*\s*\,\s*\s*data:(\S+)\s*}\s*\)",
            "to": "setStorageSync($1,$2)"
        },
        {
            "from": "removeStorageSync\(\s*{\s*key:(\S+)\s*}\s*\)",
            "to": "removeStorageSync($1)"
        }
    ],
    //#^# *xml文件中需要转换的指令
    "cXXMLs": [
        {
            "from": "(\s+)a:",
            "to": "$1wx:"
        },
        {
            "from": "(\s+)onTap(\s*)=",
            "to": "$1bindtap$2="
        },
        {
            "from": "(\s+)onLongTap(\s*)=",
            "to": "$1bindlongTap$2="
        },
        {
            "from": "(\s+)onInput(\s*)=",
            "to": "$1bindinput$2="
        },
        {
            "from": "(\s+)onChange(\s*)=",
            "to": "$1bindchange$2="
        },
        {
            "from": "(\s+)onFocus(\s*)=",
            "to": "$1bindfocus$2="
        },
        {
            "from": "(\s+)onBlur(\s*)=",
            "to": "$1bindblur$2="
        },
        {
            "from": "(\s+)onConfirm(\s*)=",
            "to": "$1bindconfirm$2="
        },
        {
            "from": "(\s+)onSubmit(\s*)=",
            "to": "$1bindsubmit$2="
        },
        {
            "from": "(\s+)onReset(\s*)=",
            "to": "$1bindreset$2="
        },
        {
            "from": "(\s+)onTouchStart(\s*)=",
            "to": "$1bindtouchstart$2="
        },
        {
            "from": "(\s+)onTouchMove(\s*)=",
            "to": "$1bindtouchmove$2="
        },
        {
            "from": "(\s+)onTouchEnd(\s*)=",
            "to": "$1bindtouchend$2="
        },
        {
            "from": "(\s+)onTouchCancel(\s*)=",
            "to": "$1bindtouchcancel$2="
        },
        {
            "from": "(\s+)onLongTap(\s*)=",
            "to": "$1bindlongtap$2="
        },
        {
            "from": "(\s+)onMarkerTap(\s*)=",
            "to": "$1bindmarkertap$2="
        },
        {
            "from": "(\s+)onCalloutTap(\s*)=",
            "to": "$1bindcallouttap$2="
        },
        {
            "from": "(\s+)onControlTap(\s*)=",
            "to": "$1bindcontroltap$2="
        },
        {
            "from": "(\s+)onScrollToLower(\s*)=",
            "to": "$1bindscrolltolower$2="
        },
        {
            "from": "(\s+)onRegionChange(\s*)=",
            "to": "$1bindregionchange$2="
        }
    ],
    //#^#json文件中需要转换的属性
    "cJSONs": [
        {
            "from": "(\s+\W)titleBarColor(\W)",
            "to": "$1navigationBarTextStyle$2"
        },
        {
            "from": "(\s+\W)defaultTitle(\W)",
            "to": "$1navigationBarTitleText$2"
        },
        {
            "from": "(\s+\W)pullRefresh(\W)",
            "to": "$1enablePullDownRefresh$2"
        },
        {
            "from": "(\s+\W)allowsBounceVertical(\W)",
            "to": "$1disableScroll$2"
        },
        {
            "from": "(\s+\W)textColor(\W)",
            "to": "$1color$2"
        },
        {
            "from": "(\s+\W)name(\W)",
            "to": "$1text$2"
        },
        {
            "from": "(\s+\W)icon(\W)",
            "to": "$1iconPath$2"
        },
        {
            "from": "(\s+\W)icon(\W)",
            "to": "$1iconPath$2"
        },
        {
            "from": "(\s+\W)activeIcon(\W)",
            "to": "$1selectedIconPath$2"
        }
    ],

    /*#^#特殊替换规则
        path：文件相对根目录的绝对路径
        rps：特殊替换规则
            type 转换类型 默认字符串类型
            注意 可将需要替换的前后文本直接黏贴进来，注意替换的文本开头缩进与原文一致
    #^#*/

    "cSRs": [
        {
            //#^# 每个文件都需要的特殊替换规则
            "path": "all",
            "rps": [
                {
                    "type" :"RegExp",
                    "from": `export default`,
                    "to": `module.exports = `
                }
            ]
        },
        {
            "path": "/pages/PowerStation/index.js",
            "rps": [
                //#^# 微信小程序获取不到地理位置信息 所以注释掉
                {
                "from": `location.address`,
                "to": `//location.address`
                }
            ]
        }

	]
}