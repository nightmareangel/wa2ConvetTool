/*#^#
微信转支付宝配置信息
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
                    "kfrom": "title",
                    //#^#转换类型 ChangeKEY：修改后的属性名
                    "kto": "content"
                },
                {
                    "type": "ChangeKEY",
                    "kfrom": "icon",
                    "kto": "type"
                }
            ]
        },
        {
            "pname":"showModal",
            "rps": [
                {
                    "type": "DelKEY",
                    //#^#删除的属性名
                    "kfrom": "showCancel"
                }
            ]
        },
        {
            "pname":"showModal",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "confirmText",
                    "kto": "confirmButtonText"
                },
                {
                    "type": "ChangeKEY",
                    "kfrom": "cancelText",
                    "kto": "cancelButtonText"
                }
            ]
        },
        {
            "pname":"showActionSheet",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "itemList",
                    "kto": "items"
                }
            ]
        },
        {
            "pname":"setTextAlign",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "align",
                    "kto": "textAlign"
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
                    "kfrom": "filePath",
                    "kto": "url"
                }
            ]
        },
        {
            "pname":"httpRequest",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "header",
                    "kto": "headers"
                }
            ]
        },
        {
            "pname":"uploadFile",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "name",
                    "kto": "fileName"
                }
            ]
        },
        {
            "pname":"connectSocket",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "header",
                    "kto": "headers"
                }
            ]
        },
        {
            "pname":"setClipboard",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "data",
                    "kto": "text"
                }
            ]
        },
        {
            "pname":"makePhoneCall",
            "rps": [
                {
                    "type": "ChangeKEY",
                    "kfrom": "phoneNumber",
                    "kto": "number"
                }
            ]
        }

    ],
    //#^#转换的扩展名
    "csuffixs": [
        {
            "from": "wxml",
            "to": "axml"
        },
        {
            "from": "wxss",
            "to": "acss"
        }
    ],
    //#^#js文件中需要转换的方法
    "cmethods": [
        {
            "from": "request",
            "to": "httpRequest"
        },
        {
            "from": "login",
            "to": "getAuthCode"
        },
        {
            "from": "showModal\(\{\^_\^",
            "to": "alert({"
        },
        {
            "from": "showModal",
            "to": "confirm"
        },
        {
            "from": "getUserInfo",
            "to": "getAuthUserInfo"
        },
        {
            "from": "requestPayment",
            "to": "tradePay"
        },
        {
            "from": "saveImageToPhotosAlbum",
            "to": "saveImage"
        },
        {
            "from": "setNavigationBarTitle",
            "to": "setNavigationBar"
        },
        {
            "from": "setNavigationBarColor",
            "to": "setNavigationBar"
        },
        {
            "from": "getClipboardData",
            "to": "getClipboard"
        },
        {
            "from": "setClipboardData",
            "to": "setClipboard"
        },
        {
            "from": "canvasToTempFilePath",
            "to": "toTempFilePath"
        },
        {
            "from": "scanCode",
            "to": "scan"
        },
        {
            "from": "closeBLEConnection",
            "to": "disconnectBLEDevice"
        },
        {
            "from": "getStorageSync\(\s*(\S+)\s*\)",
            "to": "getStorageSync({key:$1}).data"
        },
        {
            "from": "setStorageSync\(\s*(\S+)\s*\,\s*(\S+)\s*\)",
            "to": "setStorageSync({key:$1,data:$2})"
        },
        {
            "from": "removeStorageSync\(\s*(\S+)\s*\)",
            "to": "removeStorageSync({key:$1})"
        }
    ],
    //#^# *xml文件中需要转换的指令
    "cXXMLs": [
        {
            "from": "(^||\s+)wx:",
            "to": "$1a:"
        },
        {
            "from": "(^||\s+)bindtap(\s*)=",
            "to": "$1onTap$2="
        },
        {
            "from": "(^||\s+)bindlongTap(\s*)=",
            "to": "$1onLongTap$2="
        },
        {
            "from": "(^||\s+)bindinput(\s*)=",
            "to": "$1onInput$2="
        },
        {
            "from": "(^||\s+)bindchange(\s*)=",
            "to": "$1onChange$2="
        },
        {
            "from": "(^||\s+)bindinput(\s*)=",
            "to": "$1onInput$2="
        },
        {
            "from": "(^||\s+)bindfocus(\s*)=",
            "to": "$1onFocus$2="
        },
        {
            "from": "(^||\s+)bindblur(\s*)=",
            "to": "$1onBlur$2="
        },
        {
            "from": "(^||\s+)bindconfirm(\s*)=",
            "to": "$1onConfirm$2="
        },
        {
            "from": "(^||\s+)bindsubmit(\s*)=",
            "to": "$1onSubmit$2="
        },
        {
            "from": "(^||\s+)bindreset(\s*)=",
            "to": "$1onReset$2="
        },
        {
            "from": "(^||\s+)bindtouchstart(\s*)=",
            "to": "$1onTouchStart$2="
        },
        {
            "from": "(^||\s+)bindtouchmove(\s*)=",
            "to": "$1onTouchMove$2="
        },
        {
            "from": "(^||\s+)bindtouchend(\s*)=",
            "to": "$1onTouchEnd$2="
        },
        {
            "from": "(^||\s+)bindtouchcancel(\s*)=",
            "to": "$1onTouchCancel$2="
        },
        {
            "from": "(^||\s+)bindlongtap(\s*)=",
            "to": "$1onLongTap$2="
        },
        {
            "from": "(^||\s+)bindmarkertap(\s*)=",
            "to": "$1onMarkerTap$2="
        },
        {
            "from": "(^||\s+)bindcallouttap(\s*)=",
            "to": "$1onCalloutTap$2="
        },
        {
            "from": "(^||\s+)bindcontroltap(\s*)=",
            "to": "$1onControlTap$2="
        },
        {
            "from": "(^||\s+)bindscrolltolower(\s*)=",
            "to": "$1onScrollToLower$2="
        },
        {
            "from": "(^||\s+)bindregionchange(\s*)=",
            "to": "$1onRegionChange$2="
        }
    ],
    //#^#json文件中需要转换的属性
    "cJSONs": [
        {
            "from": "(^||\s+\W)navigationBarTextStyle(\W)",
            "to": "$1titleBarColor$2"
        },
        {
            "from": "(^||\s+\W)navigationBarTitleText(\W)",
            "to": "$1defaultTitle$2"
        },
        {
            "from": "(^||\s+\W)enablePullDownRefresh(\W)",
            "to": "$1pullRefresh$2"
        },
        {
            "from": "(^||\s+\W)disableScroll(\W)",
            "to": "$1allowsBounceVertical$2"
        },
        {
            "from": "(^||\s+\W)color(\W)",
            "to": "$1textColor$2"
        },
        {
            "from": "(^||\s+\W)text(\W)",
            "to": "$1name$2"
        },
        {
            "from": "(^||\s+\W)iconPath(\W)",
            "to": "$1icon$2"
        },
        {
            "from": "(^||\s+\W)iconPath(\W)",
            "to": "$1icon$2"
        },
        {
            "from": "(^||\s+\W)selectedIconPath(\W)",
            "to": "$1activeIcon$2"
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
                    "from": `module.exports =`,
                    "to": `export default `
                }
            ]
        },
        {
            "path": "/pages/PowerStation/index.js",
            "rps": [
                //#^# 微信小程序获取不到地理位置信息 所以注释掉
                {
                "from": `//location.address`,
                "to": `location.address`
                }
            ]
        }
	]
}