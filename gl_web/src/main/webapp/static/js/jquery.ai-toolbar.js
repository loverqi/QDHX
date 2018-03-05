;(function ($) {
    var itemClickHandler = {
        itemNew: function () {
        },
        itemEdit: function () {
        },
        itemDelete: function () {
        },
        itemSearch: function () {
        },
        itemExcel: function () {
        },
        itemChart: function () {
        },
        itemImport: function () {

        }

    };
    $.AIToolbar = function (options, element) {
        this.$el = $(element);
        this._init(options);
    };

    $.AIToolbar.defaults = {
        theme: "dhx_web",
        iconPath: "/static/imgs/toolbar/",
        //对应boolean数组,[新增,编辑,删除,明细,转存Excel,图表统计],默认显示所有
        visibleItems: undefined,
        itemClickHandler: itemClickHandler
    };
    $.AIToolbar.prototype = {
        _init: function (options) {
            this.options = $.extend(true, {}, $.AIToolbar.defaults, options);
            var id = this.$el.attr("id");
            var toolbar = new dhtmlXToolbarObject(id, this.options.theme);
            var itemNames = ["新增", "编辑", "删除", "明细", "转存Excel", "统计图表", "批量导入"];
            var itemIds = ["ai_new", "ai_edit", "ai_delete", "ai_search", "ai_excel", "ai_chart", "ai_import"];
            var itemIcons = ["iconNew", "iconEdit", "iconDelete", "iconSearch", "iconExcel", "iconChart", "iconExcel"];
            toolbar.setIconPath(this.options.iconPath);
            if (this.options.visibleItems == undefined) {
                this.options.visibleItems = [true, true, true, true, true, true, false];
            }
            var index = 0;
            for (var i = 0; i < itemIds.length; i++) {
                if (this.options.visibleItems[i]) {
                    toolbar.addButton(itemIds[i], index++, itemNames[i], itemIcons[i] + ".gif", itemIcons[i] + "_dis.gif")
                    if (i != itemIds.length - 1) {
                        toolbar.addSeparator("sep" + index, index++);
                    }
                }
            }

            var width = this.$el.find('div.dhx_toolbar_btn').parent().width();
            this.$el.width(width + 5);
            var self = this;
            toolbar.attachEvent("onClick", function (itemId) {
                self._clickHandle(itemId);
            });
        },
        _clickHandle: function (itemId) {
            var self = this;
            switch (itemId) {
                case "ai_new":
                    self.options.itemClickHandler.itemNew();
                    break;
                case "ai_edit":
                    self.options.itemClickHandler.itemEdit();
                    break;
                case "ai_delete":
                    self.options.itemClickHandler.itemDelete();
                    break;
                case "ai_search":
                    self.options.itemClickHandler.itemSearch();
                    break;
                case "ai_excel":
                    self.options.itemClickHandler.itemExcel();
                    break;
                case "ai_chart":
                    self.options.itemClickHandler.itemChart();
                    break;
                case "ai_import":
                    self.options.itemClickHandler.itemImport();
            }
        }
    };
    /**
     *
     * 控制台Log打印,防止IE浏览器不识别
     * @param message
     */
    var logError = function (message) {
        if (window.console) {
            window.console.error(message);
        }
    };

    $.fn.AIToolbar = function (options) {
        if (typeof options == 'string') {
            var args = Array.prototype.slice.call(arguments, 1);
            this.each(function () {
                var instance = $.data(this, 'AIToolbar');
                if (!instance) {
                    logError("cannot call methods on AIToolbar prior to initialization; " +
                        "attempted to call method '" + options + "'");
                    return;
                }
                if (!$.isFunction(instance[options]) || options.charAt(0) === "_") {
                    logError("no such method '" + options + "' for AIToolbar instance");
                    return;
                }
                instance[options].apply(instance, args);
            });
        } else {
            this.each(function () {
                var instance = $.data(this, 'AIToolbar');
                if (instance) {
                    instance._init();
                } else {
                    $.data(this, 'AIToolbar', new $.AIToolbar(options, this));
                }

            });
        }
        return this;
    };
})(jQuery);