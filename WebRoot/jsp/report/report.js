/**
 * 
 */
define(function(require, exports) {
  var $pattern = require('../../vendor/jsfx/pattern.js');
  var $util = require('../../vendor/jsfx/util.js');
  var $jq = require('../../vendor/jsfx/jquery-1.8.2-sea.js');
  var $json = require('../../vendor/jsfx/json2.js');

  // (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
  // (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
  function datefmt(date, fmt) {
    var o = {
      "M+": date.getMonth() + 1,                 //月份   
      "d+": date.getDate(),                    //日   
      "h+": date.getHours(),                   //小时   
      "m+": date.getMinutes(),                 //分   
      "s+": date.getSeconds(),                 //秒   
      "q+": Math.floor((date.getMonth() + 3) / 3), //季度   
      "S": date.getMilliseconds()             //毫秒   
    };
    if (/(y+)/.test(fmt))
      fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
      if (new RegExp("(" + k + ")").test(fmt))
        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
  }

  function dateadd(date, interval, period) {
    date = convertToDate(date);
    switch (period.toLowerCase()) {
      case "years": return new Date(date.setFullYear(date.getFullYear() + interval));
      case "months": return new Date(date.setMonth(date.getMonth() + interval));
      case "days": return new Date(date.setDate(date.getDate() + interval));
      case "weeks": return new Date(date.setDate(date.getDate() + 7 * interval));
      case "hours": return new Date(date.setHours(date.getHours() + interval));
      case "minutes": return new Date(date.setMinutes(date.getMinutes() + interval));
      case "seconds": return new Date(date.setSeconds(date.getSeconds() + interval));
      case "ss": return new Date(date.setMilliseconds(date.getMilliseconds() + interval));
    }
  };

  String.prototype.replaceAll = function(s1, s2) {
    return this.replace(new RegExp(s1, "gm"), s2);
  };

  function convertToDate(expr) {
    if (typeof expr == 'string') {
      expr = expr.replaceAll('-', '/');//將字符中的-替換為/,原因是IE或其它瀏覽器不支持-符號的Date.parse()  
      return new Date(Date.parse(expr));
    } else {
      return expr;
    }
  };

  function datediff(fromDate, toDate, interval) {
    /*
     * DateFormat month/day/year hh:mm:ss ex. datediff('01/01/2011
     * 12:00:00','01/01/2011 13:30:00','seconds');
     */
    var second = 1000, minute = second * 60, hour = minute * 60, day = hour * 24, week = day * 7;
    fromDate = new Date(fromDate);
    toDate = new Date(toDate);
    var timediff = toDate - fromDate;
    if (isNaN(timediff)) return NaN;
    switch (interval) {
      case "years": return toDate.getFullYear() - fromDate.getFullYear();
      case "months": return (
          (toDate.getFullYear() * 12 + toDate.getMonth())
          -
          (fromDate.getFullYear() * 12 + fromDate.getMonth())
      );
      case "weeks": return Math.floor(timediff / week);
      case "days": return Math.floor(timediff / day);
      case "hours": return Math.floor(timediff / hour);
      case "minutes": return Math.floor(timediff / minute);
      case "seconds": return Math.floor(timediff / second);
      default: return undefined;
    }
  }

  /*
  ||
  ||
  ||
  */
  function Chart(elementId, chartType) {
    this.elementId_ = elementId;
    this.chartType_ = chartType;    // 图类型

    this.createCharts();
  }

  Chart.STYLE = {
    LINE: 0,
    BAR: 1
  };

  Chart.prototype.createCharts = function() {
    this.charts_ = new Array(4);

    var titles = ['GBE流量', 'GBE服务数', 'QAM流量', 'QAM服务数'];
    var yTitles = ['流量(K)', 'GBE服务数', '流量(K)', 'QAM服务数'];
    var tips = ['K', '', 'K', ''];
    var ids = ['gbeb', 'gben', 'qamb', 'qamn'];

    for (var i = 0; i < 4; i++) {
      this.charts_[i] = new Highcharts.Chart({
        chart: {
          type: this.chartType_ == Chart.STYLE.LINE ? 'line' :'column',
          zoomType: 'xy',
          renderTo: ids[i] + this.elementId_
        },
        plotOptions: {
          series: {
            animation: false
          }
        },
        title: {
          text: titles[i]
        },
        subtitle: {
          text: ''
        },
        xAxis: [{
          categories: this.scales_
        }],
        yAxis: { // Primary yAxis
          title: {
            text: yTitles[i]
          },
          plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
          }]
        },
        tooltip: {
          valueSuffix: tips[i],
          shared: false
        },
        legend: {
          layout: 'vertical',
          align: 'right',
          verticalAlign: 'middle',
          borderWidth: 0
        }
      });
    }
  }

  function BarChart(elementId, lhs, rhs) {
    $pattern.base(this, elementId, Chart.STYLE.BAR);
    this.lhs_ = lhs;
    this.rhs_ = rhs;
  }

  $pattern.inherit(BarChart, Chart);

  /**
  */
  function LineChart(elementId, lhs, rhs, period) {
    this.scales_ = null;

    this.lhs_ = lhs;
    this.rhs_ = rhs;

    this.interval_ = 1;
    this.period_ = 'minutes';

    this.xfmt_ = '';

    switch (period) {
      default:
      case 0: // 1分钟
        this.period_ = 'minutes';
        this.interval_ = 1;
        this.xfmt_ = 'hh:mm';
        break;
      case 1: // 5分钟
        this.period_ = 'minutes';
        this.interval_ = 5;
        this.xfmt_ = 'hh:mm';
        break;
      case 2: // 30分钟，不能大于1小时
        this.period_ = 'minutes';
        this.interval_ = 30;
        this.xfmt_ = 'hh:mm';
        break;
      case 3: // 1小时
        this.period_ = 'hours';
        this.interval_ = 1;
        this.xfmt_ = 'hh:mm';
        break;
      case 4: // 一天
        this.period_ = 'days';
        this.interval_ = 1;
        this.xfmt_ = 'yyyy/MM/dd';
        break;
      case 5: // 一周
        this.period_ = 'weeks';
        this.interval_ = 1;
        this.xfmt_ = 'yyyy/MM/dd';
        break;
      case 6: // 一月
        this.period_ = 'months';
        this.interval_ = 1;
        this.xfmt_ = 'yyyy/MM';
        break;
      case 7: // 年
        this.period_ = 'years';
        this.interval_ = 1;
        this.xfmt_ = 'yyyy';
        break;
    }

    this.buildTimeScales();
    $pattern.base(this, elementId, Chart.STYLE.LINE);
    //
  };

  $pattern.inherit(LineChart, Chart);

  /*
   * 获取时间线 @returns 返回时间刻度数组
   */
  LineChart.prototype.buildTimeScales = function() {
    var lhs = this.lhs_;
    var rhs = this.rhs_;

    var size = datediff(lhs, rhs, this.period_);

    // 构建x时间刻度
    this.scales_ = new Array(size + 1);
    var d = convertToDate(lhs);
    for (var i = 0; i < this.scales_.length; i++) {
      d = dateadd(d, this.interval_, this.period_);
      this.scales_[i] = datefmt(d, this.xfmt_);
    }
  }

  /*
  返回时间数组索引
  */
  LineChart.prototype.getTimeScaleIndex = function(ct) {
    var d = datediff(this.lhs_, ct, this.period_);
    d = Math.floor(d / this.interval_);
    return d;
  }

  /*
   * 定义单ip详细流量线，共画四个图，gbe流量+gbe服务数+qam流量+qam服务数，gbe8线，slot9线 走势图：
   * gbs_,gns_,qbs,qns_是每个设备的gbe和slot口按时间刻度的详细，2维数组长度是时间区间/时间刻度 合计图：
   * gbs_,gns_,qbs,qns_是每个设备的gbe和slot口按时间的汇总，2维数组长度是1
   */
  function IpLineChart(elementId, lhs, rhs, period) {
    $pattern.base(this, elementId, lhs, rhs, period);

    this.nodeId_ = "";
    this.ip_ = "";
    this.gbs_ = new Array(8); // gbe流量图显示8线
    this.gns_ = new Array(8);
    this.qbs_ = new Array(9); // slot流量图显示9线
    this.qns_ = new Array(9);
  }

  $pattern.inherit(IpLineChart, LineChart);

  IpLineChart.prototype.build = function(data) {
    // 因为ip详细数据较多，仅支持一个ip
    if ($jq.isArray(data)) {
      return;
    }

    // 填充数据
    var obj = data;//$json.parse(data);
    this.nodeId_ = obj.id;
    this.ip_ = obj.ip;
    var gbe = obj.gbe;
    var qam = obj.qam;

    for (var i = 0; i < 8; ++i) {
      this.gbs_[i] = new Array(this.scales_.length);
      this.gns_[i] = new Array(this.scales_.length);

      // 初始化
      for (var j = 0; j < this.scales_.length ; ++j) {
        this.gbs_[i][j] = 0;
        this.gns_[i][j] = 0;
      }
    }

    for (var i = 0; i < 9; ++i) {
      this.qbs_[i] = new Array(this.scales_.length);
      this.qns_[i] = new Array(this.scales_.length);

      // 初始化
      for (var j = 0; j < this.scales_.length ; ++j) {
        this.qbs_[i][j] = 0;
        this.qns_[i][j] = 0;
      }
    }

    for (var i = 0; i < gbe.length ; ++i) {
      var idx = this.getTimeScaleIndex(gbe[i].ct);
      for (var j = 0; j < 8; ++j) {
        this.gbs_[j][idx] = gbe[i].b[j];
        this.gns_[j][idx] = gbe[i].n[j];
      }
    }

    for (var i = 0; i < qam.length; ++i) {
      var idx = this.getTimeScaleIndex(qam[i].ct);
      for (var j = 0; j < 9; ++j) {
        this.qbs_[j][idx] = qam[i].b[j];
        this.qns_[j][idx] = qam[i].n[j];
      }
    }

    for (var i = 0; i < 8; ++i) {
      this.charts_[0].addSeries({ name: 'gbe-' + (i + 1), data: this.gbs_[i] }, false);
      this.charts_[1].addSeries({ name: 'gbe-' + (i + 1), data: this.gns_[i] }, false);
    }

    for (var i = 0; i < 9; ++i) {
      this.charts_[2].addSeries({ name: 'qam-' + (i + 1), data: this.qbs_[i] }, false);
      this.charts_[3].addSeries({ name: 'qam-' + (i + 1), data: this.qns_[i] }, false);
    }

    for (var i = 0; i < 4; ++i)
      this.charts_[i].redraw();
  }

  /*
  [{"nid":"a89c0829-9d2d-4d1a-996e-07bbdcfdd246","ip":"","gbe":{"b":"1","n":"0","ct":"2013/11/2314:18:24"},"qam":{"b":"0","n":"0","ct":"2013/11/23"}}]
   */
  /*
  NodeDetailChart.prototype.buildBars = function(data) {
    // 因为ip详细数据较多，仅支持一个ip
    if ($jq.isArray(data)) {
      return;
    }
  
    // 填充数据
    var obj = JSON.parse(data);
    this.nodeId_ = obj.nid;
    this.ip_ = obj.ip_;
    var gbe = obj.gbs;
    var qam = obj.qbs;
  
    for (var i = 0; i < 8; ++i) {
      this.gbs_[i] = obj.gbe.b[j];
      this.gns_[j] = obj.gbe.n[j];
    }
  
    for (var i = 0; i < 9; ++i) {
      this.qbs_[i] = obj.qam.b[j];
      this.qns_[j] = obj.qam.n[j];
    }
  }*/

  /**
   * 构建走势图表
   */
  /*
  NodeDetailChart.prototype.build = function(data) {
    this.buildLines(data);
  }
  */

  /*
   * 定义ip和group线，此时共画四个图，gbe流量+gbe服务数+qam流量+qam服务数，每ip或group各一线 走势图：
   * gbs_,gns_,qbs_,qns_是每个设备或group按时间刻度的汇总，数组长度是时间区间/时间刻度 合计图
   * gbs_,gns_,qbs_,qns_是每个设备或group按时间的总汇总，数组长度是1
   */
  function TargetLine() {
    this.targetId_;   /* nodeid或者groupid */
    this.targetName_; /* ip或者组名 */
    this.gbs_ = null;
    this.gns_ = null;
    this.qbs_ = null;
    this.qns_ = null;
  }

  function MultiTargetsLineChart(elementId, lhs, rhs, period, chartType) {
    $pattern.base(this, elementId, lhs, rhs, period, chartType);
    this.targets_ = null; // TargetLine的数组，指定多个设备时，显示在一张图，用于比较
  };

  $pattern.inherit(MultiTargetsLineChart, LineChart);

  MultiTargetsLineChart.prototype.build = function(data) {
    var obj = data;//$json.parse(data);
    if ($jq.isArray(obj)) {
      this.targets_ = new Array(obj.length);
      for (var i = 0; i < obj.length; ++i)
        this.targets_[i] = new TargetLine();
    }
    else {
      this.targets_ = new Array();
      this.targets_.push(new TargetLine());

      obj = [].concat(obj);
    }

    for (var i = 0; i < obj.length; ++i) {
      this.targets_[i].gbs_ = new Array(this.scales_.length);
      this.targets_[i].gns_ = new Array(this.scales_.length);
      this.targets_[i].qbs_ = new Array(this.scales_.length);
      this.targets_[i].qns_ = new Array(this.scales_.length);

      // 初始化
      for (var j = 0; j < this.scales_.length ; ++j) {
        this.targets_[i].gbs_[j] = 0;
        this.targets_[i].gns_[j] = 0;
        this.targets_[i].qbs_[j] = 0;
        this.targets_[i].qns_[j] = 0;
      }

      var target = obj[i];

      this.targets_[i].targetId_ = target.id;
      this.targets_[i].targetName_ = target.name;

      for (var j = 0; j < target.gbe.length; ++j) {
        var idx = this.getTimeScaleIndex(target.gbe.ct);
        this.targets_[i].gbs_[j][idx] = target.gbe.b;
        this.targets_[i].gns_[j][idx] = target.gbe.n;
        this.targets_[i].qbs_[j][idx] = target.qam.b;
        this.targets_[i].qns_[j][idx] = target.qam.n;
      }
    }
  }

  /*
  || 直方图（汇总+比较）
  ||
  */
  function MultiTargetsBarChart(elementId) {
    $pattern.base(this, elementId);
    this.targets_ = null; // TargetLine的数组，指定多个设备时，显示在一张图，用于比较
  };

  $pattern.inherit(MultiTargetsBarChart, BarChart);

  MultiTargetsBarChart.prototype.build = function(data) {
    var obj = data;//$json.parse(data);
    if ($jq.isArray(obj)) {
    }
    else {
      obj = [].concat(obj);
    }

    this.gbs_ = new Array(obj.length);
    this.gns_ = new Array(obj.length);
    this.qbs_ = new Array(obj.length);
    this.qns_ = new Array(obj.length);
    this.scales_ = new Array(obj.length);

    for (var i = 0; i < obj.length; ++i) {
      var target = obj[i];
      this.scales_[i] = target.name != null ? target.name : ((target.ip != null) ? target.ip : '');
      this.gbs_[i] = target.gbe.b;
      this.gns_[i] = target.gbe.n;
      this.qbs_[i] = target.qam.b;
      this.qns_[i] = target.qam.n;
    }
  }

  // 导出对象
  exports.Chart = Chart;
  exports.IpLineChart = IpLineChart;
  exports.MultiTargetsLineChart = MultiTargetsLineChart;
  exports.MultiTargetsBarChart = MultiTargetsBarChart;

  exports.runIp = function(nodes, ips, lhs, rhs, period, chartType) {
    debugger;
    var ct = chartType.toLowerCase();
    if (ct == 'line') {
      var ss = nodes.split(',');
      if (ss.length == 0)
        return;

      var ipss = ips.split(',');
      var self = this;

      var idx = 0;
      var fn = function() {
        var chart = new IpLineChart(ipss[idx], lhs, rhs, period);
        $jq.ajax({
          type: 'post',
          url: exports.host + '/nodes/report_querySingleIpStatistics.sip',
          // '"nodeId":"a89c0829-9d2d-4d1a-996e-07bbdcfdd246","start":"2013/11/23 14:00","end":"2013/11/23 14:35","period":"0","chartType":"0"',
          dataType: 'json',
          data: {
            nodeId: ss[idx],
            ip: ipss[idx],
            start: lhs,
            end: rhs,
            period: period,
            chartType: 0
          },
          success: function(data) {
            chart.build(data[0]);

            if (idx < ss.length - 1) {
              setTimeout(0, fn, ++idx);
            }
          }
        });
      }

      setTimeout(fn, 0);
    }
    else if (ct == 'bar') {
      var chart = new MultiTargetsLineChart(targets, lhs, rhs, period);
      $jq.ajax({
        type: 'POST',
        url: exports.host + '/nodes/report_queryMultiIpsStatistics.sip',
        data: '"nodeIds":"a89c0829-9d2d-4d1a-996e-07bbdcfdd246,a89c0829-9d2d-4d1a-996e-07bbdcfdd247","start":"2013/11/23 14:00","end":"2013/11/23 14:35","period":"0","chartType":"1"',
        success: function(data) {
          if (data == '') {

          }
          else {
            chart.build(data);
          }
        }
      });
    }
  }

  exports.runGroup = function(groups, gnames, lhs, rhs, period, chartType) {
    debugger;
    var ct = chartType.toLowerCase();
    if (ct == 'line') {
      var chart = new MultiTargetsLineChart("", lhs, rhs, period);
      $jq.ajax({
        type: 'POST',
        url: exports.host + '/nodes/report_queryGroupsStatistics.sip',
        //data: '"groupIds":"40288b394283bac5014283bb76b60001","start":"2013/11/23 14:00","end":"2013/11/23 14:35","period":"0","chartType":"0"',
        data: {
          groupIds: groups,
          start: lhs,
          end: lhs,
          period: period,
          chartType: 0
        },
        success: function(data) {
          if (data != null) {
            chart.build(data);
          }
        }
      });
    }
    else if (ct == 'bar') {
      var chart = new MultiTargetsBarChart("", lhs, rhs);
      $jq.ajax({
        type: 'POST',
        url: exports.host + '/nodes/report_queryGroupsStatistics.sip',
        data: '"groupIds":"40288b394283bac5014283bb76b60001","start":"2013/11/23 14:00","end":"2013/11/23 14:35","period":"0","chartType":"1"',
        success: function(data) {
          if (data == '') {

          }
          else {
            chart.build(data);
          }
        }
      });
    }
    else {
      ;
    }
  }

  var host = '';
  exports.host = host;

  exports.test = {};
  exports.test.run_ip_detail = function() {
    var chart = new IpLineChart('192.168.11.45', '2013/11/23 14:00', '2013/11/23 14:35', 0);
    var data = '{"id":"a89c0829-9d2d-4d1a-996e-07bbdcfdd246","ip":"192.168.11.45","gbe":[{"b":["1","0","0","0","0","0","0","0"],"n":["0","0","0","0","0","0","0","0"],"ct":"2013/11/23 14:18:24"},{"b":["0","0","0","0","0","0","0","0"],"n":["0","0","0","0","0","0","0","0"],"ct":"2013/11/23 14:20:23"},{"b":["0","0","0","0","0","0","0","0"],"n":["0","0","0","0","0","0","0","0"],"ct":"2013/11/23 14:21:46"},{"b":["0","0","0","0","0","0","0","0"],"n":["0","0","0","0","0","0","0","0"],"ct":"2013/11/23 14:22:48"},{"b":["0","0","0","0","0","0","0","0"],"n":["0","0","0","0","0","0","0","0"],"ct":"2013/11/23 14:23:34"},{"b":["1","0","0","0","0","0","0","0"],"n":["0","0","0","0","0","0","0","0"],"ct":"2013/11/23 14:28:37"},{"b":["0","0","0","0","0","0","0","0"],"n":["0","0","0","0","0","0","0","0"],"ct":"2013/11/23 14:29:17"},{"b":["0","0","0","0","0","0","0","0"],"n":["0","0","0","0","0","0","0","0"],"ct":"2013/11/23 14:30:13"},{"b":["0","0","0","0","0","0","0","0"],"n":["0","0","0","0","0","0","0","0"],"ct":"2013/11/23 14:31:01"}],"qam":[{"b":["122000","0","0","0","0","0","0","0","0"],"n":["0","0","0","0","0","0","0","0","0"],"ct":"2013/11/23 14:18:00"},{"b":["112000","112000","0","112000","0","0","0","0","0"],"n":["0","0","0","0","0","0","0","0","0"],"ct":"2013/11/23 14:20:00"},{"b":["112000","0","0","0","0","0","0","0","0"],"n":["0","0","0","0","0","0","0","0","0"],"ct":"2013/11/23 14:21:00"},{"b":["112000","0","0","112000","0","0","0","0","0"],"n":["0","0","0","0","0","0","0","0","0"],"ct":"2013/11/23 14:22:00"},{"b":["112000","0","114000","0","0","0","0","0","0"],"n":["0","0","0","0","0","0","0","0","0"],"ct":"2013/11/23 14:23:00"}]}';
    chart.build(data);
  }

  exports.test.run_multi_ips_trend = function() {
    var chart = new MultiTargetsLineChart('', '2013/11/23 14:00', '2013/11/23 14:35', 0);
    var data = '[{"id":"a89c0829-9d2d-4d1a-996e-07bbdcfdd246","ip":"192.168.11.45","gbe":[{"b":"1","n":"0","ct":"2013/11/23 14:18:00"},{"b":"0","n":"0","ct":"2013/11/23 14:20:00"},{"b":"101","n":"0","ct":"2013/11/23 14:21:00"},{"b":"1","n":"0","ct":"2013/11/23 14:22:00"},{"b":"0","n":"0","ct":"2013/11/23 14:23:00"},{"b":"1","n":"0","ct":"2013/11/23 14:28:00"},{"b":"0","n":"0","ct":"2013/11/23 14:29:00"},{"b":"0","n":"0","ct":"2013/11/23 14:30:00"},{"b":"0","n":"0","ct":"2013/11/23 14:31:00"}],"qam":[{"b":"122000","n":"0","ct":"2013/11/23 14:18:00"},{"b":"336000","n":"0","ct":"2013/11/23 14:20:00"},{"b":"450000","n":"0","ct":"2013/11/23 14:21:00"},{"b":"448000","n":"0","ct":"2013/11/23 14:22:00"},{"b":"338000","n":"0","ct":"2013/11/23 14:23:00"}]},{"id":"a89c0829-9d2d-4d1a-996e-07bbdcfdd247","ip":"192.168.11.46","gbe":[{"b":"150","n":"0","ct":"2013/11/23 14:21:00"}],"qam":[]}]';
    chart.build(data);
  }

  exports.test.run_multi_ips_total = function() {
    var chart = new MultiTargetsBarChart('', '2013/11/23 14:00', '2013/11/23 14:35');
    var data = '[{"id":"a89c0829-9d2d-4d1a-996e-07bbdcfdd246","ip":"192.168.11.45","gbe":[{"b":"104","n":"0","ct":"2013/11/23 14:22:00"}],"qam":[{"b":"1694000","n":"0","ct":"2013/11/23 14:21:00"}]},{"id":"a89c0829-9d2d-4d1a-996e-07bbdcfdd247","ip":"192.168.11.46","gbe":[{"b":"150","n":"0","ct":"2013/11/23 14:21:00"}],"qam":[]}]';
    chart.build(data);
  }

  exports.test.run_groups_trend = function() {
    var chart = new MultiTargetsLineChart('', '2013/11/23 14:00', '2013/11/23 14:35', 0);
    var data = '[{"id":"40288b394283bac5014283bb76b60001","name":"test1","gbe":[{"b":"1","n":"0","ct":"2013/11/23 14:18:00"},{"b":"0","n":"0","ct":"2013/11/23 14:20:00"},{"b":"101","n":"0","ct":"2013/11/23 14:21:00"},{"b":"1","n":"0","ct":"2013/11/23 14:22:00"},{"b":"0","n":"0","ct":"2013/11/23 14:23:00"},{"b":"1","n":"0","ct":"2013/11/23 14:28:00"},{"b":"0","n":"0","ct":"2013/11/23 14:29:00"},{"b":"0","n":"0","ct":"2013/11/23 14:30:00"},{"b":"0","n":"0","ct":"2013/11/23 14:31:00"}],"qam":[{"b":"122000","n":"0","ct":"2013/11/23 14:18:00"},{"b":"336000","n":"0","ct":"2013/11/23 14:20:00"},{"b":"450000","n":"0","ct":"2013/11/23 14:21:00"},{"b":"448000","n":"0","ct":"2013/11/23 14:22:00"},{"b":"338000","n":"0","ct":"2013/11/23 14:23:00"}]}]';
    chart.build(data);
  }

  exports.test.run_groups_total = function() {
    var chart = new MultiTargetsBarChart('', '2013/11/23 14:00', '2013/11/23 14:35');
    var data = '[{"id":"40288b394283bac5014283bb76b60001","name":"test1","gbe":[{"b":"104","n":"0","ct":"2013/11/23 14:31:00"}],"qam":[{"b":"1694000","n":"0","ct":"2013/11/23 14:23:00"}]}]';
    chart.build(data);
  }
});
