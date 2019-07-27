package com.example.drawturtlescore;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.drawturtlescore.adapter.PlayerRvAdapter;
import com.example.drawturtlescore.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvPlayers)
    RecyclerView mRvPlayers;
    @BindView(R.id.btnAddPlayer)
    Button mBtnAddPlayer;

    private PlayerRvAdapter mRvAdapter;
    private List<String> mPlayerNames = new ArrayList<>();
    private String[] mNames = new String[]{
            "帅过蟋蟀",
            "浮华沧桑",
            "烟味衬衫",
            "嗱媳妇换糖糖",
            "忆丶凉生",
            "风瑾如画",
            "未闻花名",
            "蔸蔸侑糖",
            "微尘",
            "裸奔吧蝸牛",
            "日落时的沧桑",
            "徒步づ鬼門關",
            "阿猫小姐",
            "花环少女",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRv();
    }

    private void initRv() {
        mRvPlayers.setLayoutManager(new LinearLayoutManager(this));
        mRvPlayers.setItemAnimator(new DefaultItemAnimator());
        mRvPlayers.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRvAdapter = new PlayerRvAdapter(R.layout.item_player);
        mRvPlayers.setAdapter(mRvAdapter);
        View emptyView = LayoutInflater.from(this).inflate(R.layout.view_empty, null);
        mRvAdapter.setEmptyView(emptyView);
    }

    @OnClick(R.id.btnAddPlayer)
    public void onViewClicked() {
        showAddPlayerDialog();
    }

    public void showAddPlayerDialog() {
        final View textEntryView = LayoutInflater.from(this).inflate(R.layout.dialog_add_player, null);
        final EditText etPlayerName = (EditText) textEntryView.findViewById(R.id.etPlayerName);
        final ImageView ivRandom = (ImageView) textEntryView.findViewById(R.id.ivRandom);
        ivRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etPlayerName.setText(mNames[new Random().nextInt(13)]);
            }
        });
        new AlertDialog.Builder(this)
                .setTitle("添加玩家")
                .setView(textEntryView)
                .setNegativeButton("取消", null)
                .setPositiveButton("添加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String playerName = etPlayerName.getText().toString().trim();
                        if (TextUtils.isEmpty(playerName)){
                            ToastUtil.showToastShort(MainActivity.this, "昵称不能为空！");
                            return;
                        }
                        if (!mRvAdapter.getData().contains(playerName)) {
                            mPlayerNames.add(playerName);
                            mRvAdapter.setNewData(mPlayerNames);
                        } else {
                            ToastUtil.showToastShort(MainActivity.this, "玩家已存在，请换个昵称！");
                        }
                    }
                })
                .setCancelable(false).show();
    }
}
