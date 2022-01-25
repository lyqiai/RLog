import './LogApp.css';
import 'antd/dist/antd.css'
import React, {Component} from 'react'
import {Select, DatePicker, Button, Table} from "antd";
import {SearchOutlined} from '@ant-design/icons';
import axios from "axios";
import moment from 'moment';

const {Option} = Select;

const baseURL = "http://api.localhost/";

const tableColumn = [
    {title: 'identity', dataIndex: 'identity', key: 'identity'},
    {title: 'level', dataIndex: 'level', key: 'level'},
    {title: 'thread', dataIndex: 'threadName', key: 'threadName'},
    {title: 'content', dataIndex: 'content', key: 'content'},
    {title: 'packageName', dataIndex: 'packageName', key: 'packageName'},
    {title: 'versionName', dataIndex: 'versionName', key: 'versionName'},
    {title: 'versionCode', dataIndex: 'versionCode', key: 'versionCode'},
    {title: 'time', dataIndex: 'time', key: 'time'},
];

const pageSize = 20;

class LogApp extends Component {
    constructor(props) {
        super(props);
        this.state = {
            packages: [],
            identities: [],
            levels: [],
            packageName: "",
            identity: "",
            level: "",
            time: null,
            page: 1,
            total: 0,
            list: [],
            loading: false,
        }
    }

    componentDidMount() {
        this.queryPackages();
        this.queryIdentity();
        this.queryLevel();
        this.queryLogs();
    }

    async queryPackages() {
        const response = await axios.get(`${baseURL}log/getAllPackages`);
        if (response.status === 200 && response.data.code === 0) {
            this.setState({
                packages: response.data.data
            })
        }
    }

    async queryIdentity() {
        const response = await axios.get(`${baseURL}log/getAllIdentity`);
        if (response.status === 200 && response.data.code === 0) {
            this.setState({
                identities: response.data.data
            })
        }
    }

    async queryLevel() {
        if (this.state.loading) {
            return
        }

        this.setState({
            loading: true
        });
        const response = await axios.get(`${baseURL}log/getAllLevel`);
        if (response.status === 200 && response.data.code === 0) {
            this.setState({
                levels: response.data.data
            })
        }
        this.setState({
            loading: false
        })
    }

    async queryLogs() {
        const time = this.state.time?.format("yyyy-MM-DD");
        const response = await axios.get(`${baseURL}log/getLogs`, {
            params: {
                packageName: this.state.packageName,
                identity: this.state.identity,
                level: this.state.level,
                page: this.state.page - 1,
                pageSize: pageSize,
                time: time,
            }
        });
        if (response.status === 200 && response.data.code === 0) {
            this.setState({
                list: response.data.data.data,
                total: response.data.data.total,
            });
        }
    }

    onPageChange(page) {
        this.setState({
            page: page
        }, () => {
            this.queryLogs();
        });
    }

    setPackageName(value) {
        this.setState({
            packageName: value
        })
    }

    setIdentity(value) {
        this.setState({
            identity: value
        })
    }

    setLevel(value) {
        this.setState({
            level: value
        })
    }

    setTime(value) {
        this.setState({
            time: value
        })
    }

    render() {
        return (
            <div className="App">
                <header className="header">
                    <span className="search-text">应用：</span>
                    <Select className="search-select"
                            value={this.state.packageName}
                            onChange={this.setPackageName.bind(this)}
                            allowClear={true}>
                        {this.state.packages.map(item => <Option key={item}>{item}</Option>)}
                    </Select>

                    <span className="search-text">Identity：</span>
                    <Select className="search-select"
                            value={this.state.identity}
                            onChange={this.setIdentity.bind(this)}
                            allowClear={true}>
                        {this.state.identities.map(item => <Option key={item}>{item}</Option>)}
                    </Select>

                    <span className="search-text">Level：</span>
                    <Select className="search-select"
                            value={this.state.level}
                            onChange={this.setLevel.bind(this)}
                            allowClear={true}>
                        {this.state.levels.map(item => <Option key={item}>{item}</Option>)}
                    </Select>

                    <span className="search-text">日期：</span>
                    <DatePicker
                        className="date-picker"
                        value={this.state.time}
                        onChange={this.setTime.bind(this)}/>

                    <Button
                        className="search"
                        type="primary"
                        icon={<SearchOutlined/>}
                        disabledDate={disabledDate}
                        onClick={() => this.onPageChange(1)}>搜索</Button>
                </header>

                <Table
                    columns={tableColumn}
                    dataSource={this.state.list}
                    rowKey={record => record.id}
                    loading={this.state.loading}
                    pagination={{
                        current: this.state.page,
                        pageSize: pageSize,
                        total: this.state.total,
                        onChange: this.onPageChange.bind(this),
                    }}/>
            </div>
        )
    }
}

function disabledDate(current) {
    return current && current < moment().endOf('day');
}

export default LogApp;
